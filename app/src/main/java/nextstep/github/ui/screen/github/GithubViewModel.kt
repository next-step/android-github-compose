package nextstep.github.ui.screen.github

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nextstep.github.MainApplication
import nextstep.github.data.repository.GithubRepository
import nextstep.github.ui.uistate.UiState


class GithubViewModel(
    private val githubRepository: GithubRepository,
) : ViewModel() {

    private val _repositoryUiState =
        MutableStateFlow<UiState<List<RepositoryUiState>>>(UiState.Loading)
    val repositoryUiState: StateFlow<UiState<List<RepositoryUiState>>> =
        _repositoryUiState.asStateFlow()

    private val _errorFlow = MutableSharedFlow<String>(replay = 1)
    val errorFlow = _errorFlow.asSharedFlow()

    init {
        viewModelScope.launch {
            fetchRepositories("next-step")
        }
    }

    suspend fun fetchRepositories(
        organization: String,
    ) {
        githubRepository.getRepositories(
            organization = organization
        ).onSuccess { repositories ->
            if (repositories.isEmpty()) {
                _repositoryUiState.value = UiState.Empty
            } else {
                _repositoryUiState.value = UiState.Success(repositories.toUiStateList())
            }
        }.onFailure {
            _repositoryUiState.value = UiState.Failure(it.message ?: "Unknown error")
            notifyFailure(it.message ?: "Unknown error")
        }
    }

    fun onRetry() {
        viewModelScope.launch {
            fetchRepositories("next-step")
        }
    }

    private fun notifyFailure(message: String) {
        _errorFlow.tryEmit(message)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val githubRepository = (this[APPLICATION_KEY] as MainApplication)
                    .appContainer
                    .githubRepository
                GithubViewModel(githubRepository)
            }
        }
    }
}
