package nextstep.github.ui.github

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nextstep.github.GithubApplication
import nextstep.github.data.repository.GithubRepository

class GithubRepoViewModel(
    private val githubRepoRepository: GithubRepository,
) : ViewModel() {

    private val _githubUiState: MutableStateFlow<GithubRepoUiState> = MutableStateFlow(GithubRepoUiState())
    val githubUiState: StateFlow<GithubRepoUiState> = _githubUiState.asStateFlow()

    private val _errorFlow = MutableSharedFlow<Throwable>()
    val errorFlow = _errorFlow.asSharedFlow()

    init {
        fetchRepositories()
    }

    private fun fetchRepositories() {
        viewModelScope.launch {
            _githubUiState.update { it.copy(isLoading = true) }
            runCatching {
                val repositories = githubRepoRepository.getRepositories("next-step")
                _githubUiState.update { it.copy(repositories = repositories, isLoading = false) }
            }.onFailure { throwable ->
                _githubUiState.update { it.copy(isLoading = false) }
                _errorFlow.emit(throwable)
            }
        }
    }

    fun retryGithubRepo() {
        fetchRepositories()
    }


    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val githubRepoRepository =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GithubApplication)
                        .appContainer
                        .githubRepository
                GithubRepoViewModel(githubRepoRepository)
            }
        }
    }
}