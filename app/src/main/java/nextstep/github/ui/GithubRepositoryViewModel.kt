package nextstep.github.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nextstep.github.GithubApplication
import nextstep.github.domain.GetGithubRepositoryUseCase
import nextstep.github.ui.model.GithubRepositoryModel
import nextstep.github.ui.model.toGithubRepositoryModel

class GithubRepositoryViewModel(
    private val getGithubRepositoryUseCase: GetGithubRepositoryUseCase
) : ViewModel() {

    private val _state = GithubRepositoryStateImpl()
    val state: GithubRepositoryState = _state

    init {
        loadRepositories()
    }

    fun loadRepositories() {
        viewModelScope.launch {
            getGithubRepositoryUseCase.invoke(
                organization = NEXT_STEP_ORGANIZATION,
                preLoad = {
                    _state.repositoryUiState.update { GithubRepositoryState.RepositoryUiState.Loading }
                }
            )
                .onSuccess { data ->
                    val result = data.mapNotNull { it.toGithubRepositoryModel() }
                    _state.repositoryUiState.update {
                        if (result.isEmpty()) {
                            GithubRepositoryState.RepositoryUiState.Empty
                        } else {
                            GithubRepositoryState.RepositoryUiState.Data(result)
                        }
                    }
                }
                .onError { t ->
                    _state.repositoryUiState.update {
                        GithubRepositoryState.RepositoryUiState.Error(t)
                    }
                }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val getGithubRepositoryUseCase =
                    (this[APPLICATION_KEY] as GithubApplication).appContainer.provideGetGithubRepositoryUseCase()
                GithubRepositoryViewModel(getGithubRepositoryUseCase)
            }
        }
    }
}

interface GithubRepositoryState {
    val repositoryUiState: StateFlow<RepositoryUiState>

    sealed interface RepositoryUiState {
        data object Idle : RepositoryUiState
        data object Loading : RepositoryUiState
        data object Empty : RepositoryUiState
        data class Data(val items: List<GithubRepositoryModel>) : RepositoryUiState
        data class Error(val t: Throwable? = null) : RepositoryUiState
    }
}

class GithubRepositoryStateImpl(
    override val repositoryUiState: MutableStateFlow<GithubRepositoryState.RepositoryUiState> = MutableStateFlow(
        GithubRepositoryState.RepositoryUiState.Idle
    )
) : GithubRepositoryState

sealed interface GithubRepositoryEvent {
    data object ShowSnackBar : GithubRepositoryEvent
}

private const val NEXT_STEP_ORGANIZATION = "next-step"