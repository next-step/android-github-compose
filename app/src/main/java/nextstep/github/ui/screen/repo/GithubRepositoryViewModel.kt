package nextstep.github.ui.screen.repo

import android.util.Log
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
            GithubEvent.Init -> loadNextStepRepositories()
            GithubEvent.OnRetryClick -> loadNextStepRepositories()
        }
    }

    private fun loadNextStepRepositories() {
        Log.d("koni", "loadNextStepRepositories")
        viewModelScope.launch {
            githubRepository.getNextStepRepositories()
                .onSuccess { repositories ->
                    Log.d("koni", "get $repositories")
                    _state.update {
                        it.copy(
                            repositories = repositories,
                            loading = false,
                            exception = null
                        )
                    }
                }
                .onFailure { error ->
                    Log.d("koni", "error $error")
                    _state.update {
                        it.copy(
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
