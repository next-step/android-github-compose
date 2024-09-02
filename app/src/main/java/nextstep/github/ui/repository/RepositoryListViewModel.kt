package nextstep.github.ui.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nextstep.github.GithubApplication
import nextstep.github.data.GithubRepository

class RepositoryListViewModel(
    private val repository: GithubRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<RepositoryListUiState>(RepositoryListUiState.Loading)
    val uiState: StateFlow<RepositoryListUiState> = _uiState.asStateFlow()

    init {
        fetchRepositories()
    }

    fun fetchRepositories() {
        _uiState.value = RepositoryListUiState.Loading

        viewModelScope.launch {
            repository
                .getRepositories(ORGANIZATION)
                .onSuccess {
                    _uiState.value = if (it.isEmpty()) RepositoryListUiState.Empty
                    else RepositoryListUiState.Success(it)
                }
                .onFailure { _uiState.value = RepositoryListUiState.Error }
        }
    }

    companion object {
        private const val ORGANIZATION = "next-step"

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val repository =
                    (this[APPLICATION_KEY] as GithubApplication).appContainer.githubRepository
                RepositoryListViewModel(repository)
            }
        }
    }

}
