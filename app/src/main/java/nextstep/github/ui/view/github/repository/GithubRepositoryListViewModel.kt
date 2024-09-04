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
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import nextstep.github.GithubApplication
import nextstep.github.data.GithubRepository
import nextstep.github.ui.model.GithubRepositoryModel
import nextstep.github.ui.model.toUiModel

class GithubRepositoryListViewModel(
    private val repository: GithubRepository,
) : ViewModel() {

    private val repositories: MutableStateFlow<List<GithubRepositoryModel>?> = MutableStateFlow(null)

    private val _error: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val error: StateFlow<Boolean> = _error.asStateFlow()

    val uiState: StateFlow<GithubRepositoryListUiState> = repositories.map {
        when {
            it == null -> {
                GithubRepositoryListUiState.Loading
            }
            it.isEmpty() -> {
                GithubRepositoryListUiState.NotFound
            }
            else -> {
                GithubRepositoryListUiState.Found(repositories = it)
            }
        }
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        GithubRepositoryListUiState.Loading,
    )

    init {
        fetchRepositories()
    }

    private fun fetchRepositories() {
        viewModelScope.launch(CoroutineExceptionHandler { _, _ ->
            _error.value = true
        }) {
            _error.value = false
            repositories.value = repository.getRepositories("next-step")
                .map { it.toUiModel() }
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
