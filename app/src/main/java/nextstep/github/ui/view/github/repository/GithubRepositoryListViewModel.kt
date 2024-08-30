package nextstep.github.ui.view.github.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import nextstep.github.GithubApplication
import nextstep.github.data.GithubRepository
import nextstep.github.model.GithubRepositoryDto

class GithubRepositoryListViewModel(
    private val repository: GithubRepository,
) : ViewModel() {
    val repositories: StateFlow<List<GithubRepositoryDto>> = flow {
        emit(repository.getRepositories("next-step"))
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val githubRepository = (this[APPLICATION_KEY] as GithubApplication).appContainer.githubRepository
                GithubRepositoryListViewModel(
                    repository = githubRepository
                )
            }
        }
    }
}
