package nextstep.github.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import nextstep.github.GitHubApplication
import nextstep.github.domain.usecase.GetNextStepRepositoriesUseCase

class GitHubRepositoryListViewModel(
    private val getNextStepRepositoriesUseCase: GetNextStepRepositoriesUseCase,
) : ViewModel() {
    private val _uiState: MutableStateFlow<GitHubRepositoryListState> =
        MutableStateFlow(GitHubRepositoryListState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _errorEvent = Channel<Throwable>()
    val errorEvent = _errorEvent.receiveAsFlow()

    init {
        fetchRepositories()
    }

    fun fetchRepositories() = viewModelScope.launch {
        _uiState.value = GitHubRepositoryListState.Loading

        getNextStepRepositoriesUseCase()
            .onSuccess {
                _uiState.value = if (it.isEmpty()) {
                    GitHubRepositoryListState.Empty
                } else {
                    GitHubRepositoryListState.Repositories(it)
                }
            }
            .onFailure {
                _errorEvent.send(it)
            }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as GitHubApplication
                val repository = application.appContainer.githubRepository

                GitHubRepositoryListViewModel(
                    GetNextStepRepositoriesUseCase(repository)
                )
            }
        }
    }
}
