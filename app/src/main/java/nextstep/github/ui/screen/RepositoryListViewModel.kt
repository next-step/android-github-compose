package nextstep.github.ui.screen

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
import nextstep.github.MainApplication
import nextstep.github.data.repository.GithubRepository
import nextstep.github.ui.model.RepositoryModel
import nextstep.github.ui.model.toModel

class RepositoryListViewModel(
    private val githubRepository: GithubRepository,
) : ViewModel() {

    private val _repositories = MutableStateFlow<List<RepositoryModel>>(emptyList())
    val repositories : StateFlow<List<RepositoryModel>> = _repositories.asStateFlow()

    fun getRepositories(
        organization : String = "next-step"
    ) {
        viewModelScope.launch {
            try {
                _repositories.emit(
                    githubRepository.getRepositories(organization)
                        .map { it.toModel() }
                )

            }catch (e : Exception){
                // TODO
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val githubRepository = (this[APPLICATION_KEY] as MainApplication)
                    .appContainer
                    .githubRepository
                RepositoryListViewModel(githubRepository)
            }
        }
    }
}
