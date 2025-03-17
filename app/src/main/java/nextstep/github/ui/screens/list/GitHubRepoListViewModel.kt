package nextstep.github.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import nextstep.github.GithubApplication
import nextstep.github.data.repositories.GithubRepoRepository

class GitHubRepoListViewModel(
    private val githubRepoRepository: GithubRepoRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<GitHubRepoListUiState> =
        MutableStateFlow(GitHubRepoListUiState.Loading)
    val uiState: StateFlow<GitHubRepoListUiState> = _uiState.asStateFlow()

    private val _sideEffect: MutableSharedFlow<GitHubRepoListSideEffect> = MutableSharedFlow(replay = 1)
    val sideEffect: SharedFlow<GitHubRepoListSideEffect> = _sideEffect.asSharedFlow()

    init {
        observeRepositories()
    }

    fun observeRepositories() {
        viewModelScope.launch {
            githubRepoRepository.getGitHubReposStream(organization = NEXT_STEP_ORGANIZATION)
                .catch { throwable ->
                    _sideEffect.emit(GitHubRepoListSideEffect.ShowError(throwable))
                }
                .collect { repositories ->
                    _sideEffect.emit(GitHubRepoListSideEffect.HideError)

                    when {
                        repositories.isEmpty() -> _uiState.value = GitHubRepoListUiState.Empty
                        else -> _uiState.value = GitHubRepoListUiState.Success(repositories)
                    }
                }
        }
    }

    companion object {
        private const val NEXT_STEP_ORGANIZATION = "next-step"

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val githubRepository =
                    (this[APPLICATION_KEY] as GithubApplication)
                        .appContainer
                        .githubRepoRepository

                GitHubRepoListViewModel(githubRepository)
            }
        }
    }
}
