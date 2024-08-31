package nextstep.github.ui.repo

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import nextstep.github.MainApplication
import nextstep.github.core.domain.GeOrganizationRepositoryUseCase
import nextstep.github.core.model.Organization

class GithubRepoViewModel(
    private val savedStateHandle: SavedStateHandle,
    private val geOrganizationRepositoryUseCase: GeOrganizationRepositoryUseCase,
) : ViewModel() {
    private val organization =
        savedStateHandle
            .getStateFlow<String?>(KEY_ORGANIZATION, null)

    private val refresh = MutableSharedFlow<Unit>(replay = 1)

    @OptIn(ExperimentalCoroutinesApi::class)
    val uiState =
        refresh
            .flatMapLatest {
                organization
                    .filterNotNull()
            }.mapNotNull { organization ->
                Organization.fromValue(organization)
            }.flatMapLatest { organization ->
                flowOf(
                    geOrganizationRepositoryUseCase(organization).getOrThrow(),
                )
            }.map {
                if (it.isEmpty()) {
                    GithubRepoUiState.Empty
                } else {
                    GithubRepoUiState.Success(it)
                }
            }.catch {
                Log.e(TAG, "Failed to fetch repositories", it)
                showErrorMessage(it.message ?: "Failed to fetch repositories")
            }.stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5_000),
                initialValue = GithubRepoUiState.Loading,
            )

    private val _effect = MutableSharedFlow<GithubRepoEffect>()
    val effect = _effect.asSharedFlow()

    init {
        refresh()
    }

    private fun refresh() {
        viewModelScope.launch {
            refresh.emit(Unit)
        }
    }

    fun retry() {
        refresh()
    }

    private fun showErrorMessage(message: String) {
        viewModelScope.launch {
            _effect.emit(GithubRepoEffect.ShowErrorMessage(message))
        }
    }

    companion object {
        private const val TAG = "GithubRepoViewModel"

        val Factory: ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    val getNextStepRepositoryUseCase =
                        (this[APPLICATION_KEY] as MainApplication)
                            .appContainer
                            .geOrganizationRepositoryUseCase
                    val savedStateHandle =
                        createSavedStateHandle().apply {
                            /**
                             * compose navigation 통해 path 전달 받는 것과 동일하게 동작하도록 임시 구현
                             */
                            set(KEY_ORGANIZATION, Organization.NEXT_STEP.value)
                        }
                    GithubRepoViewModel(
                        savedStateHandle,
                        getNextStepRepositoryUseCase,
                    )
                }
            }
    }
}
