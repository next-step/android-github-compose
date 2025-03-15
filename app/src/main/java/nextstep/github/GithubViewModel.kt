package nextstep.github

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import nextstep.github.data.repository.GithubRepoRepository
import nextstep.github.model.GithubRepo

class GithubViewModel(
    githubRepoRepository: GithubRepoRepository
) : ViewModel() {

    val githubRepositories: StateFlow<List<GithubRepo>> =
        githubRepoRepository.getRepos("next-step")
            .catch {
                Log.d("asdf", it.message.toString())
            }
            .stateIn(
                scope = viewModelScope,
                initialValue = emptyList(),
                started = SharingStarted.WhileSubscribed(5000L)
            )
}
