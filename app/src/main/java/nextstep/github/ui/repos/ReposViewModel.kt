package nextstep.github.ui.repos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import nextstep.github.NextStepApp
import nextstep.github.ui.usecase.GetGitHubRepositoryUseCase


internal class ReposViewModel(
    private val getGitHubRepositoryUseCase: GetGitHubRepositoryUseCase
) : ViewModel() {

    private val refreshTrigger = MutableStateFlow(TARGET_ORGANIZATION)

    @OptIn(ExperimentalCoroutinesApi::class)
    val repos: StateFlow<ReposUiState> = refreshTrigger
        .flatMapLatest { organization ->
            flow { emit(getGitHubRepositoryUseCase(organization = organization)) }
                .map { ReposUiState.Success(it) }
                .catch<ReposUiState> { emit(ReposUiState.Error(it)) }
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ReposUiState.Loading
        )

    fun searchRepos(organization: String = TARGET_ORGANIZATION) {
        refreshTrigger.update { organization }
    }

    companion object {
        private const val TARGET_ORGANIZATION = "next-step"

        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val getGitHubRepositoryUseCase = (this[APPLICATION_KEY] as NextStepApp)
                    .appContainer
                    .getGitHubRepositoryUseCase

                ReposViewModel(getGitHubRepositoryUseCase)
            }
        }
    }
}

