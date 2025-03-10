package nextstep.github.ui.screens.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import nextstep.github.GithubApplication
import nextstep.github.data.repositories.GithubRepository

class RepositoryListViewModel(
    private val githubRepository: GithubRepository,
) : ViewModel() {

    private val _uiState: MutableStateFlow<RepositoryListUiState> =
        MutableStateFlow(RepositoryListUiState.Loading)
    val uiState: StateFlow<RepositoryListUiState> = _uiState.asStateFlow()

    private var observeRepositoriesJob: Job? = null

    init {
        observeRepositories()
    }

    fun observeRepositories() {
        observeRepositoriesJob?.cancel()

        observeRepositoriesJob = viewModelScope.launch {
            githubRepository.getRepositoriesStream(organization = NEXT_STEP_ORGANIZATION)
                .catch {
                    _uiState.value = RepositoryListUiState.Error
                }
                .collect { repositories ->
                    when {
                        repositories.isEmpty() -> _uiState.value = RepositoryListUiState.Empty
                        else -> _uiState.value = RepositoryListUiState.Success(repositories)
                    }
                }
        }
    }

    companion object {
        private const val NEXT_STEP_ORGANIZATION = "next-step"

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
