package nextstep.github.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nextstep.github.GitHubApplication
import nextstep.github.data.NextStepRepository
import nextstep.github.model.LoadState
import nextstep.github.model.NextStepRepositoryEntity

class RepositoryListViewModel(private val nextStepRepository: NextStepRepository) : ViewModel() {

    private val _repositories = MutableStateFlow<List<NextStepRepositoryEntity>>(emptyList())
    val repositories: StateFlow<List<NextStepRepositoryEntity>> = _repositories.asStateFlow()

    private val _loadState = MutableStateFlow<LoadState>(LoadState.Loading)
    val loadState: StateFlow<LoadState> = _loadState.asStateFlow()

    init {
        viewModelScope.launch {
            getRepositories("next-step")
        }
    }

    private suspend fun getRepositories(organization: String) {
        _loadState.value = LoadState.Loading
        try {
            val repositoryEntities = nextStepRepository.getRepositories(organization)
            _repositories.update { repositoryEntities }
            _loadState.value = LoadState.Success
        } catch (e: Exception) {
            _loadState.value = LoadState.Error
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val nextStepRepository = (this[APPLICATION_KEY] as GitHubApplication)
                    .appContainer
                    .nextStepRepository
                RepositoryListViewModel(nextStepRepository)
            }
        }
    }
}
