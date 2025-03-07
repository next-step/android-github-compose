package nextstep.github.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
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
        fun provideFactory(
            githubRepository: GithubRepository,
        ): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return GithubViewModel(githubRepository) as T
            }
        }
    }
}
