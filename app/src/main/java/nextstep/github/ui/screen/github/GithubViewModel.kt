package nextstep.github.ui.screen.github

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import nextstep.github.data.network.ApiResult
import nextstep.github.di.MainApplication
import nextstep.github.domain.usecase.GetGithubRepoUseCase
import nextstep.github.ui.screen.github.list.GithubRepositoryUiState

class GithubViewModel(
    private val getGithubRepoUseCase: GetGithubRepoUseCase
) : ViewModel() {

    private val _uiState =
        MutableStateFlow<GithubRepositoryUiState>(GithubRepositoryUiState.Loading)
    val uiState: StateFlow<GithubRepositoryUiState> = _uiState

    fun getRepositories(organization: String) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                _uiState.value = GithubRepositoryUiState.Loading
                delay(300)
                when (val result = getGithubRepoUseCase(organization)) {
                    is ApiResult.Success -> {
                        if (result.value.isEmpty()) {
                            _uiState.value = GithubRepositoryUiState.Empty
                        } else {
                            _uiState.value =
                                GithubRepositoryUiState.Success(
                                    githubRepositories = result.value
                                )
                        }
                    }

                    is ApiResult.Error -> {
                        _uiState.value = GithubRepositoryUiState.Error
                        Log.e("GithubViewModel", "Error: ${result.code} ${result.exception}")
                    }
                }
            } catch (e: Exception) {
                _uiState.value = GithubRepositoryUiState.Error
                Log.e("GithubViewModel", "Error: ${e.message}")
            }
        }
    }

    fun setLoadingUiState() {
        _uiState.value = GithubRepositoryUiState.Loading
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val githubRepository = (this[APPLICATION_KEY] as MainApplication)
                    .appContainer
                    .githubRepository

                val getGithubRepoUseCase = GetGithubRepoUseCase(githubRepository)

                GithubViewModel(getGithubRepoUseCase)
            }
        }
    }
}
