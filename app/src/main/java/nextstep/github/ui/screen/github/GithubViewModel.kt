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
import nextstep.github.domain.GithubRepositoryUseCase
import nextstep.github.domain.RepositoryResult
import nextstep.github.ui.screen.github.model.RepositoryUiModel
import nextstep.github.ui.screen.github.model.toUiStateList
import nextstep.github.ui.uistate.UiState


class GithubViewModel(
    private val githubRepositoryUseCase: GithubRepositoryUseCase,
) : ViewModel() {

    private val _repositoryUiModel =
        MutableStateFlow<UiState<List<RepositoryUiModel>>>(UiState.Loading)
    val repositoryUiModel: StateFlow<UiState<List<RepositoryUiModel>>> =
        _repositoryUiModel.asStateFlow()

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
        githubRepositoryUseCase.getRepositories(
            organization = organization,
        ).collect {
            when (it) {
                is RepositoryResult.Success -> {
                    if (it.data.isEmpty()) {
                        _repositoryUiModel.value = UiState.Empty
                    } else {
                        _repositoryUiModel.value = UiState.Success(it.data.toUiStateList())
                    }
                }
                is RepositoryResult.Error -> {
                    _repositoryUiModel.value = UiState.Failure(it.exception.message ?: "Unknown error")
                    notifyFailure(it.exception.message ?: "Unknown error")
                }
            }
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
                val githubRepositoryUseCase = (this[APPLICATION_KEY] as MainApplication)
                    .appContainer
                    .githubRepositoryUseCase

                GithubViewModel(githubRepositoryUseCase)
            }
        }
    }
}
