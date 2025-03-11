package nextstep.github.ui.screen.github

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import nextstep.github.MainApplication
import nextstep.github.data.model.RepositoryModel
import nextstep.github.data.repository.GithubRepository


class GithubViewModel(
    private val githubRepository: GithubRepository,
): ViewModel() {

    private val _repositoryList = MutableStateFlow<List<RepositoryModel>>(emptyList())
    val repositoryList = _repositoryList

    init {
        viewModelScope.launch {
            getRepositories("next-step")
        }
    }

    suspend fun getRepositories(
        organization: String,
    ) {
        githubRepository.getRepositories(
            organization = organization
        ).onSuccess {
            _repositoryList.value = it
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
