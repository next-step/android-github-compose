package nextstep.github.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nextstep.github.GithubApplication
import nextstep.github.data.GithubRepository
import nextstep.github.ui.model.GithubRepositoryModel
import nextstep.github.ui.model.toGithubRepositoryModel

class GithubRepositoryViewModel(
    private val githubRepository: GithubRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<GithubRepositoryState>(GithubRepositoryState())
    val state: StateFlow<GithubRepositoryState> = _state.asStateFlow()

    init {
        loadRepositories()
    }

    private fun loadRepositories() {
        viewModelScope.launch {
            githubRepository.getRepositories(organization = NEXT_STEP_ORGANIZATION,
                onPreLoad = { _state.update { it.copy(loadState = GithubRepositoryState.LoadState.Loading) } })
                .onSuccess { data ->
                    val result = data.mapNotNull { it.toGithubRepositoryModel() }
                    _state.update {
                        it.copy(
                            loadState = GithubRepositoryState.LoadState.Success,
                            repositories = result
                        )
                    }
                }
                .onError { t ->
                    _state.update {
                        it.copy(
                            loadState = GithubRepositoryState.LoadState.Error(t)
                        )
                    }
                }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val githubRepository =
                    (this[APPLICATION_KEY] as GithubApplication).appContainer.githubRepository
                GithubRepositoryViewModel(githubRepository)
            }
        }
    }
}

data class GithubRepositoryState(
    val loadState: LoadState,
    val repositories: List<GithubRepositoryModel>,
) {
    constructor() : this(
        loadState = LoadState.Idle,
        repositories = emptyList(),
    )

    sealed interface LoadState {
        data object Idle : LoadState
        data object Loading : LoadState
        data object Success : LoadState
        data class Error(val t: Throwable) : LoadState
    }
}

private const val NEXT_STEP_ORGANIZATION = "next-step"