package nextstep.github.ui.list

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
import nextstep.github.Const
import nextstep.github.GithubApplication
import nextstep.github.data.GithubRepository
import nextstep.github.ui.model.Repository
import nextstep.github.ui.model.toRepository

class GithubViewModel(
    private val githubRepository: GithubRepository
) : ViewModel() {

    private val _repositories = MutableStateFlow<List<Repository>>(emptyList())
    val repositories: StateFlow<List<Repository>> = _repositories.asStateFlow()

    init {
        getRepositories()
    }

    private fun getRepositories() {
        viewModelScope.launch {
            val repositories =
                githubRepository.getRepositories(Const.ORGANIZATION_NAME).map { it.toRepository() }
            _repositories.update { repositories }
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