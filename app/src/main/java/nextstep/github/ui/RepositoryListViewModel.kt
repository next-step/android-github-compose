package nextstep.github.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nextstep.github.GithubApplication
import nextstep.github.data.repository.GithubRepoRepository
import nextstep.github.ui.model.RepositoryListUiState

class RepositoryListViewModel(
    private val githubRepoRepository: GithubRepoRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow<RepositoryListUiState>(RepositoryListUiState.Loading(false))
    val uiState = _uiState.asStateFlow()

    fun fetchRepositories() {
        viewModelScope.launch {
            runCatching {
                githubRepoRepository.getRepositories(ORGANIZATION)
            }.onSuccess { repositories ->
                _uiState.update {
                    if (repositories.isEmpty()) {
                        RepositoryListUiState.Empty
                    } else {
                        RepositoryListUiState.Success(items = repositories)
                    }
                }
            }.onFailure {
                _uiState.update {
                    RepositoryListUiState.Loading(error = true)
                }
            }
        }
    }

    companion object {
        private const val ORGANIZATION = "next-step"

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val githubRepository = (this[APPLICATION_KEY] as GithubApplication)
                    .appContainer
                    .githubRepoRepository
                RepositoryListViewModel(githubRepository)
            }
        }
    }
}
