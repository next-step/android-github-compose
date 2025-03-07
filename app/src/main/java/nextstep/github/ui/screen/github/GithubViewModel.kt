package nextstep.github.ui.screen.github

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nextstep.github.MainApplication
import nextstep.github.data.repository.GithubRepository


class GithubViewModel(
    private val githubRepository: GithubRepository,
): ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getRepositories("next-step")
        }
    }

    suspend fun getRepositories(
        organization: String,
    ) {
        githubRepository.getRepositories(
            organization = organization
        ).onSuccess {
            Log.d("GithubViewModel", "getRepositories: $it")
        }.onFailure {
            Log.d(
                "GithubViewModel",
                "getRepositories: ${it.message}",
                it
            )
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val githubRepository = (this[APPLICATION_KEY] as MainApplication)
                    .appContainer
                    .githubRepository
                GithubViewModel(githubRepository)
            }
        }
    }
}
