package nextstep.github.ui.github

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import nextstep.github.GithubApplication
import nextstep.github.data.repository.GithubRepository
import nextstep.github.model.GithubRepo

class GithubRepoViewModel(
    private val githubRepoRepository: GithubRepository,
) : ViewModel() {

    val githubRepositories: StateFlow<List<GithubRepo>> = flow<List<GithubRepo>> {
        emit(githubRepoRepository.getRepositories("next-step"))
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val githubRepoRepository =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as GithubApplication)
                        .appContainer
                        .githubRepository
                GithubRepoViewModel(githubRepoRepository)
            }
        }
    }
}