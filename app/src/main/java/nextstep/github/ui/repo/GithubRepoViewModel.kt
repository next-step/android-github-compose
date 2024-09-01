package nextstep.github.ui.repo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nextstep.github.MainApplication
import nextstep.github.core.domain.GeOrganizationRepositoryUseCase
import nextstep.github.core.model.Organization

class GithubRepoViewModel(
    private val geOrganizationRepositoryUseCase: GeOrganizationRepositoryUseCase,
) : ViewModel() {
    private val _uiState = MutableStateFlow<GithubRepoUiState>(GithubRepoUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private val _effect = MutableSharedFlow<GithubRepoEffect>()
    val effect = _effect.asSharedFlow()

    init {
        fetchRepositories()
    }

    private fun fetchRepositories() {
        viewModelScope.launch {
            geOrganizationRepositoryUseCase(Organization.NEXT_STEP)
                .onSuccess {
                    _uiState.value =
                        if (it.isEmpty()) {
                            GithubRepoUiState.Empty
                        } else {
                            GithubRepoUiState.Success(it)
                        }
                }.onFailure {
                    showErrorMessage(it.message ?: "Failed to fetch repositories")
                    Log.e(TAG, "Failed to fetch repositories", it)
                }
        }
    }

    fun retry() {
        fetchRepositories()
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
                    GithubRepoViewModel(getNextStepRepositoryUseCase)
                }
            }
    }
}
