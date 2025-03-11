package nextstep.github.ui.github

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import nextstep.github.GithubApplication
import nextstep.github.domain.usecase.GetGithubRepositoriesUseCase
import nextstep.github.model.Repository
import nextstep.github.util.Result
import java.util.concurrent.atomic.AtomicBoolean

class GithubViewModel(
    private val getGithubRepositoriesUseCase: GetGithubRepositoriesUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<GithubUiState>(GithubUiState.Loading)
    val uiState: StateFlow<GithubUiState> = _uiState.asStateFlow()

    private val _errorFlow = Channel<Throwable>()
    val errorFlow = _errorFlow.receiveAsFlow()

    init {
        fetchRepositories()
    }

    private var fetchRepositoriesJob: Job? = null
    private val isRunning = AtomicBoolean(false)

    fun fetchRepositories() {
        if (isRunning.get()) return
        if (fetchRepositoriesJob?.isActive == true) return

        isRunning.set(true)

        fetchRepositoriesJob =
            getGithubRepositoriesUseCase(organization = DEFAULT_SEARCH_KEYWORD).onEach { result ->
                when (result) {
                    Result.Loading -> {
                        _uiState.update { GithubUiState.Loading }
                    }

                    is Result.Error -> {
                        _errorFlow.send(result.exception)
                    }

                    is Result.Success<List<Repository>> -> {
                        _uiState.update {
                            when {
                                result.data.isEmpty() -> GithubUiState.EmptyRepository
                                else -> GithubUiState.Repositories(result.data)
                            }
                        }
                    }
                }
            }.launchIn(viewModelScope).apply {
                invokeOnCompletion {
                    isRunning.set(false)
                    fetchRepositoriesJob = null
                }
            }
    }

    override fun onCleared() {
        fetchRepositoriesJob?.cancel()
        super.onCleared()
    }

    companion object {
        private const val DEFAULT_SEARCH_KEYWORD = "next-step"

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val githubRepository =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GithubApplication)
                        .appContainer
                        .githubRepository

                GithubViewModel(GetGithubRepositoriesUseCase(githubRepository))
            }
        }
    }
}