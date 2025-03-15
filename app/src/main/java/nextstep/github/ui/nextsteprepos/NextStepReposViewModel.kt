package nextstep.github.ui.nextsteprepos

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import nextstep.github.GithubApplication
import nextstep.github.data.repository.GithubRepoRepository
import nextstep.github.model.GithubRepo

class NextStepReposViewModel(
    githubRepoRepository: GithubRepoRepository
) : ViewModel() {

    val nextStepRepos: StateFlow<List<GithubRepo>> =
        githubRepoRepository.getRepos("next-step")
            .catch {
                Log.d("asdf", it.message.toString())
            }
            .stateIn(
                scope = viewModelScope,
                initialValue = emptyList(),
                started = SharingStarted.WhileSubscribed(5000L)
            )

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val githubRepoRepository = (this[APPLICATION_KEY] as GithubApplication)
                    .appContainer
                    .githubRepoRepository

                NextStepReposViewModel(githubRepoRepository)
            }
        }
    }
}
