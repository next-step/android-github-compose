package nextstep.github.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nextstep.github.NextGitHubApplication
import nextstep.github.data.entity.Repository
import nextstep.github.data.repository.api.GithubRepository

class RepositoryListViewModel(
    private val repository: GithubRepository,
) : ViewModel() {

    private val _repositoryList = MutableStateFlow<PersistentList<Repository>>(persistentListOf())
    val repositoryList: StateFlow<PersistentList<Repository>> = _repositoryList.asStateFlow()

    fun loadRepositoryList() {
        viewModelScope.launch {
            _repositoryList.value = repository.getRepos().toPersistentList()
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