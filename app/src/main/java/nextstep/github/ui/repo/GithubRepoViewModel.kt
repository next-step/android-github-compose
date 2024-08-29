package nextstep.github.ui.repo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nextstep.github.MainApplication
import nextstep.github.core.data.GithubRepository
import nextstep.github.core.model.RepositoryEntity

class GithubRepoViewModel(
    private val githubRepository: GithubRepository,
) : ViewModel() {
    private val _repositories = MutableStateFlow<List<RepositoryEntity>>(emptyList())
    val repositories = _repositories.asStateFlow()

    private val _uiState = MutableStateFlow<GithubRepoUiState>(GithubRepoUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchRepositories()
    }

    private fun fetchRepositories() {
        viewModelScope.launch {
            githubRepository
                .getRepositories(DEFAULT_REPOSITORY_ORGANIZATION)
                .onSuccess {
                    _uiState.value =
                        if (it.isEmpty()) {
                            GithubRepoUiState.Empty
                        } else {
                            GithubRepoUiState.Success(it)
                        }
                }.onFailure {
                    _uiState.value =
                        GithubRepoUiState.Error(it.message ?: "Unknown error occurred")
                    Log.e(TAG, "Failed to fetch repositories", it)
                }
        }
    }

    companion object {
        private const val TAG = "GithubRepoViewModel"

        private const val DEFAULT_REPOSITORY_ORGANIZATION = "next-step"

        val Factory: ViewModelProvider.Factory =
            viewModelFactory {
                initializer {
                    val githubRepository =
                        (this[APPLICATION_KEY] as MainApplication)
                            .appContainer
                            .githubRepository
                    GithubRepoViewModel(githubRepository)
                }
            }
    }
}
