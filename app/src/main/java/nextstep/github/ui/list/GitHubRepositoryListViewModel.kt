package nextstep.github.ui.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nextstep.github.GitHubApplication
import nextstep.github.domain.model.Repository
import nextstep.github.domain.usecase.GetNextStepRepositoriesUseCase

class GitHubRepositoryListViewModel(
    private val getNextStepRepositoriesUseCase: GetNextStepRepositoriesUseCase,
) : ViewModel() {
    private val _repositories = MutableStateFlow(emptyList<Repository>())
    val repositories = _repositories.asStateFlow()

    init {
        fetchRepositories()
    }

    private fun fetchRepositories() = viewModelScope.launch {
        _repositories.update {
            getNextStepRepositoriesUseCase()
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as GitHubApplication
                val repository = application.appContainer.githubRepository

                GitHubRepositoryListViewModel(
                    GetNextStepRepositoriesUseCase(repository)
                )
            }
        }
    }
}
