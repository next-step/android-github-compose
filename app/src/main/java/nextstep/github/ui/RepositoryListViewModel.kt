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
import nextstep.github.data.repository.model.RepositoryEntity

class RepositoryListViewModel(
    private val githubRepoRepository: GithubRepoRepository
) : ViewModel() {

    private val _repositories = MutableStateFlow<List<RepositoryEntity>>(emptyList())
    val repositories = _repositories.asStateFlow()

    fun fetchRepositories() {
        viewModelScope.launch {
            _repositories.update {
                githubRepoRepository.getRepositories(ORGANIZATION)
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
