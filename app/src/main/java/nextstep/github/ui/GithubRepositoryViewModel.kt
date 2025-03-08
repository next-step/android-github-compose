package nextstep.github.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nextstep.github.GithubApplication
import nextstep.github.data.GithubRepository
import nextstep.github.ui.model.GithubRepositoryModel
import nextstep.github.ui.model.toGithubRepositoryModel

class GithubRepositoryViewModel(
    private val githubRepository: GithubRepository,
) : ViewModel() {

    private val _githubRepositories = MutableStateFlow<List<GithubRepositoryModel>>(emptyList())
    val githubRepositories = _githubRepositories.asStateFlow()

    init {
        loadRepositories()
    }

    private fun loadRepositories() {
        viewModelScope.launch {
            githubRepository.getRepositories(NEXT_STEP_ORGANIZATION)
                .onSuccess { data ->
                    val result = data.mapNotNull { it.toGithubRepositoryModel() }
                    _githubRepositories.update { result }
                }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val githubRepository = (this[APPLICATION_KEY] as GithubApplication)
                    .appContainer
                    .githubRepository
                GithubRepositoryViewModel(githubRepository)
            }
        }
    }
}

private const val NEXT_STEP_ORGANIZATION = "next-step"