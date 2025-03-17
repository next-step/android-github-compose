package nextstep.github.ui.repo_list

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
import nextstep.github.App
import nextstep.github.data.entity.RepositoryEntity
import nextstep.github.data.repository.GithubRepository

class RepositoryListViewModel(
    private val githubRepository: GithubRepository
) : ViewModel() {
    private val _repositories = MutableStateFlow<List<RepositoryEntity>>(listOf())
    val repositories = _repositories.asStateFlow()

    init {
        viewModelScope.launch {
            _repositories.update {
                githubRepository.getRepositories("next-step")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val githubRepository = (this[APPLICATION_KEY] as App)
                    .appContainer
                    .githubRepository

                RepositoryListViewModel(githubRepository)
            }
        }
    }
}
