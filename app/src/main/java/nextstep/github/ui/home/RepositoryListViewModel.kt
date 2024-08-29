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
import nextstep.github.data.GithubRepository
import nextstep.github.data.RepositoryEntity

sealed interface RepositoryUiState {
    data object Loading : RepositoryUiState
    data class Success(val repositories: List<RepositoryEntity>) : RepositoryUiState
    data class Error(val message: String) : RepositoryUiState
}

class RepositoryListViewModel(
    private val githubRepository: GithubRepository
) : ViewModel() {

    private val _repositoryUiState = MutableStateFlow<RepositoryUiState>(RepositoryUiState.Loading)
    val repositoryUiState: StateFlow<RepositoryUiState> = _repositoryUiState

    fun fetchRepositories(organization: String) {
        _repositoryUiState.value = RepositoryUiState.Loading
        viewModelScope.launch {
            githubRepository.getRepositories(organization)
                .fold(
                    onSuccess = { repositories ->
                        _repositoryUiState.value = RepositoryUiState.Success(repositories)
                    },
                    onFailure = { throwable ->
                        _repositoryUiState.value = RepositoryUiState.Error(throwable.message ?: "Unknown error")
                    }
                )
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val githubRepository = (this[APPLICATION_KEY] as GithubApplication)
                    .appContainer
                    .githubRepository
                RepositoryListViewModel(githubRepository)
            }
        }
    }
}
