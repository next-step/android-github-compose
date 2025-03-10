package nextstep.github.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import nextstep.github.NextGitHubApplication
import nextstep.github.data.repository.api.GithubRepository

class RepositoryListViewModel(
    private val repository: GithubRepository,
) : ViewModel() {

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