package nextstep.github.ui.view.github.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import nextstep.github.GithubApplication
import nextstep.github.data.GithubRepository
import nextstep.github.model.GithubRepositoryDto

class GithubRepositoryListViewModel(
    private val repository: GithubRepository,
) : ViewModel() {

    private val isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)

    private val repositories: MutableStateFlow<List<GithubRepositoryDto>> = MutableStateFlow(emptyList())

    private val error: MutableStateFlow<Boolean> = MutableStateFlow(false)

    val uiState: StateFlow<GithubRepositoryListUiState> = combine(
        isLoading,
        repositories,
        error,
    ) { isLoading, repositories, error ->
        if (isLoading) {
            GithubRepositoryListUiState.Loading
        } else if (error) {
            GithubRepositoryListUiState.Error
        } else {
            GithubRepositoryListUiState.Success(repositories)
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        GithubRepositoryListUiState.Loading
    )

    init {
        fetchRepositories()
    }

    private fun fetchRepositories() {
        viewModelScope.launch(CoroutineExceptionHandler { _, _ ->
            error.value = true
        }) {
            error.value = false
            isLoading.value = true
            repositories.value = repository.getRepositories("next-step")
            isLoading.value = false
        }
    }

    fun retry() {
        fetchRepositories()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val githubRepository = (this[APPLICATION_KEY] as GithubApplication).appContainer.githubRepository
                GithubRepositoryListViewModel(
                    repository = githubRepository
                )
            }
        }
    }
}
