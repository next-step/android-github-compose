package nextstep.github.ui.github

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nextstep.github.GithubApplication
import nextstep.github.data.api.model.RepositoryEntity
import nextstep.github.data.repository.GithubRepository

class GithubViewModel(
    private val githubRepository: GithubRepository
) : ViewModel() {

    private val _repositories = MutableStateFlow<List<RepositoryEntity>>(emptyList())
    val repositories: StateFlow<List<RepositoryEntity>> = _repositories.asStateFlow()

    init {
        fetchRepositories()
    }

    private fun fetchRepositories() {
        viewModelScope.launch {
            _repositories.update {
                githubRepository.getRepositories(DEFAULT_SEARCH_KEYWORD)
            }
        }
    }

    companion object {
        private const val DEFAULT_SEARCH_KEYWORD = "next-step"

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val githubRepository =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GithubApplication)
                        .appContainer
                        .githubRepository

                GithubViewModel(githubRepository)
            }
        }
    }
}