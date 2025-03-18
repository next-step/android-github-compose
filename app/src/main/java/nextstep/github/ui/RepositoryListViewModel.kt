package nextstep.github.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import nextstep.github.NextGitHubApplication
import nextstep.github.data.repository.api.GithubRepository
import nextstep.github.domain.usecase.CheckIsHotRepoUseCase
import nextstep.github.ui.model.RepositoryListScreenSideEffect
import nextstep.github.ui.model.RepositoryListScreenUiState

class RepositoryListViewModel(
    private val repository: GithubRepository,
    private val checkIsHotRepoUseCase: CheckIsHotRepoUseCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow<RepositoryListScreenUiState>(RepositoryListScreenUiState.Loading)
    val uiState: StateFlow<RepositoryListScreenUiState> = _uiState.asStateFlow()

    private val _sideEffect: Channel<RepositoryListScreenSideEffect> = Channel()
    val sideEffect = _sideEffect.receiveAsFlow()

    private val ceh: CoroutineExceptionHandler = CoroutineExceptionHandler { _, _ ->
        _uiState.value = RepositoryListScreenUiState.Error
        _sideEffect.trySend(RepositoryListScreenSideEffect.ShowErrorSnackBar)
    }

    fun loadRepositoryList() {
        viewModelScope.launch(ceh) {
            val repositoryList = repository.getRepos().toPersistentList()
            _uiState.value = if (repositoryList.isEmpty()) {
                RepositoryListScreenUiState.Empty
            } else {
                RepositoryListScreenUiState.Success(repositoryList)
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val appContainer = (this[APPLICATION_KEY] as NextGitHubApplication).appContainer

                val githubRepository = appContainer.githubRepository
                val checkIsHotRepoUseCase = appContainer.checkIsHotRepoUseCase

                RepositoryListViewModel(
                    repository = githubRepository,
                    checkIsHotRepoUseCase = checkIsHotRepoUseCase,
                )
            }
        }
    }
}