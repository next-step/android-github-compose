package nextstep.github.ui.screen.repo

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
import nextstep.github.data.repository.GithubRepository

class GithubRepositoryViewModel(
    private val githubRepository: GithubRepository
) : ViewModel() {

    private val _state = MutableStateFlow<GithubState>(GithubState())
    val state = _state.asStateFlow()

    fun handleEvent(event: GithubEvent) {
        when (event) {
            GithubEvent.Init -> getNextStepRepositories()
        }
    }

    private fun getNextStepRepositories() {
        viewModelScope.launch {
            githubRepository.loadNextStepRepositories()
                .onSuccess { repositories ->
                    _state.update {
                        it.copy(
                            repositories = repositories,
                            loading = false
                        )
                    }
                }
                .onFailure { error ->
                    _state.update {
                        it.copy(
                            loading = false,
                            exception = error,
                        )
                    }
                }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val exampleRepository = (this[APPLICATION_KEY] as GithubApplication)
                    .appContainer
                    .githubRepository
                GithubRepositoryViewModel(exampleRepository)
            }
        }
    }
}
