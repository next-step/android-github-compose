package nextstep.github.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import nextstep.github.GithubApplication
import nextstep.github.data.repositories.GithubRepository

class RepositoryListViewModel(
    githubRepository: GithubRepository,
) : ViewModel() {
    val uiState: StateFlow<RepositoryListUiState> =
        githubRepository.getRepositoriesStream(
            organization = NEXT_STEP_ORGANIZATION
        ).map { repositories ->
            RepositoryListUiState(repositories = repositories)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MILLIS),
            initialValue = RepositoryListUiState()
        )

    companion object {
        private const val NEXT_STEP_ORGANIZATION = "next-step"
        private const val STOP_TIMEOUT_MILLIS = 5_000L

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val githubRepository =
                    (this[APPLICATION_KEY] as GithubApplication)
                        .appContainer
                        .githubRepository

                RepositoryListViewModel(githubRepository)
            }
        }
    }
}
