package nextstep.github.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nextstep.github.GithubApplication
import nextstep.github.domain.GetGithubRepositoriesUseCase
import nextstep.github.domain.Repository

sealed interface RepositoryUiState {
    data object Loading : RepositoryUiState
    data class Success(val repositories: List<Repository>) : RepositoryUiState {
        val isEmpty: Boolean
            get() = repositories.isNullOrEmpty()
    }

}

sealed interface RepositoryErrorState {
    data object None : RepositoryErrorState
    data class Error(val message: String) : RepositoryErrorState
}


class RepositoryListViewModel(
    private val repositoryUseCase: GetGithubRepositoriesUseCase
) : ViewModel() {
    private val _repositoryUiState = MutableStateFlow<RepositoryUiState>(RepositoryUiState.Loading)
    val repositoryUiState: StateFlow<RepositoryUiState> = _repositoryUiState

    private val _repositoryErrorState =
        MutableStateFlow<RepositoryErrorState>(RepositoryErrorState.None)
    val repositoryErrorState: StateFlow<RepositoryErrorState> = _repositoryErrorState

    fun fetchRepositories() {
        _repositoryUiState.value = RepositoryUiState.Loading
        _repositoryErrorState.value = RepositoryErrorState.None
        viewModelScope.launch {
            repositoryUseCase.getRepositories()
                .fold(
                    onSuccess = { repositories ->
                        _repositoryUiState.value = RepositoryUiState.Success(repositories)
                    },
                    onFailure = { throwable ->
                        _repositoryErrorState.value =
                            RepositoryErrorState.Error(throwable.message ?: "Unknown error")
                    }
                )
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val githubRepository = (this[APPLICATION_KEY] as GithubApplication)
                    .appContainer
                    .githubRepositoryUseCase
                RepositoryListViewModel(githubRepository)
            }
        }
    }
}
