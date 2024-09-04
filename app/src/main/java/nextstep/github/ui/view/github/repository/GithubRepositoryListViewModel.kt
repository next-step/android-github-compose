package nextstep.github.ui.view.github.repository

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nextstep.github.GithubApplication
import nextstep.github.data.GithubRepository
import nextstep.github.model.GithubRepositoryDto

class GithubRepositoryListViewModel(
    private val repository: GithubRepository,
) : ViewModel() {

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _repositories: MutableStateFlow<List<GithubRepositoryDto>> = MutableStateFlow(emptyList())
    val repositories: StateFlow<List<GithubRepositoryDto>> = _repositories.asStateFlow()

    private val _error: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val error: StateFlow<Boolean> = _error.asStateFlow()

    init {
        fetchRepositories()
    }

    private fun fetchRepositories() {
        viewModelScope.launch(CoroutineExceptionHandler { _, _ ->
            _error.value = true
        }) {
            _error.value = false
            _isLoading.value = true
            _repositories.value = repository.getRepositories("next-step")
            _isLoading.value = false
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
