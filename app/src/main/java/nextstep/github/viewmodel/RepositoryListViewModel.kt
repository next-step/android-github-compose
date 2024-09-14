package nextstep.github.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import nextstep.github.GitHubApplication
import nextstep.github.data.NextStepRepository

class RepositoryListViewModel(private val nextStepRepository: NextStepRepository) : ViewModel() {
    suspend fun getRepositories(organization: String) = nextStepRepository.getRepositories(organization)

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
