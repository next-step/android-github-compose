package nextstep.github.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import nextstep.github.GithubApplication
import nextstep.github.R
import nextstep.github.data.repository.GithubRepoRepository
import nextstep.github.ui.model.RepositoryListEvent
import nextstep.github.ui.model.RepositoryListUiState

class RepositoryListViewModel(
    private val githubRepoRepository: GithubRepoRepository
) : ViewModel() {
    val uiState = getRepositoriesFlow().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = RepositoryListUiState.Loading
    )

    private val _event = MutableSharedFlow<RepositoryListEvent>()
    val event = _event.asSharedFlow()

    private fun getRepositoriesFlow(): Flow<RepositoryListUiState> {
        return flow {
            val repositories = githubRepoRepository.getRepositories(ORGANIZATION)
            if (repositories.isEmpty()) {
                emit(RepositoryListUiState.Empty)
            } else {
                emit(RepositoryListUiState.Success(items = repositories))
            }
        }.catch {
            emit(RepositoryListUiState.Loading)
            _event.tryEmit(
                RepositoryListEvent.ShowSnackBar(
                    msgRes = R.string.repository_list_fetch_error,
                    actionLabelRes = R.string.retry
                )
            )
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
