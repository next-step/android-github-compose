package nextstep.github.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import nextstep.github.GithubApplication
import nextstep.github.data.GithubRepository

class GithubViewModel(
    private val githubRepository: GithubRepository
): ViewModel() {

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val githubRepository = (this[APPLICATION_KEY] as GithubApplication)
                    .appContainer
                    .githubRepository
                GithubViewModel(githubRepository)
            }
        }
    }
}