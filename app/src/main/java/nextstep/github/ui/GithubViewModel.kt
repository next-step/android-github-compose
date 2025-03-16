package nextstep.github.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nextstep.github.GithubApplication
import nextstep.github.data.GithubRepository

data class RepositoryInfo(
    val fullName: String,
    val description: String,
)

class GithubViewModel(
    private val githubRepository: GithubRepository
) : ViewModel() {

    private val _repositories: MutableStateFlow<List<RepositoryInfo>> = MutableStateFlow(emptyList())
    val repositories: StateFlow<List<RepositoryInfo>> = _repositories.asStateFlow()

    fun getRepositories(organization: String) {
        viewModelScope.launch {
            val infoList = githubRepository.getRepositories(organization)
                .map {
                    RepositoryInfo(
                        fullName = it.fullName ?: "",
                        description = it.description ?: "",
                    )
                }
            _repositories.value = infoList
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val githubRepository = (this[APPLICATION_KEY] as GithubApplication)
                    .appContainer
                    .githubRepository
                GithubViewModel(githubRepository)
            }
        }
    }
}