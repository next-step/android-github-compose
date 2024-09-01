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
import nextstep.github.model.RepositoryEntity

class RepositoryListViewModel(
    private val repository: GithubRepository,
) : ViewModel() {

    private val _repositories = MutableStateFlow<List<RepositoryEntity>>(listOf())
    val repositories: StateFlow<List<RepositoryEntity>> = _repositories.asStateFlow()

    init {
        setup()
    }

    fun setup() {
        viewModelScope.launch {
            _repositories.value = repository.getRepositories(ORGANIZATION)
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
