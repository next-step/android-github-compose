package nextstep.github.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nextstep.github.NextGitHubApplication
import nextstep.github.data.repository.api.GithubRepository
import nextstep.github.ui.model.RepositoryListScreenUiState

class RepositoryListViewModel(
    private val repository: GithubRepository,
) : ViewModel() {

    private val _uiState = MutableStateFlow<RepositoryListScreenUiState>(RepositoryListScreenUiState.Loading)
    val uiState: StateFlow<RepositoryListScreenUiState> = _uiState.asStateFlow()

    fun loadRepositoryList() {
        viewModelScope.launch {
            _uiState.value = RepositoryListScreenUiState.Success(
                repository.getRepos().toPersistentList()
            )
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val githubRepository = (this[APPLICATION_KEY] as NextGitHubApplication)
                    .appContainer
                    .githubRepository
                RepositoryListViewModel(githubRepository)
            }
        }
    }
}