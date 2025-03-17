package nextstep.github.ui

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
import nextstep.github.GithubApplication
import nextstep.github.data.GithubRepository

class GithubViewModel(
    private val githubRepository: GithubRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<GithubUiState> = MutableStateFlow(GithubUiState.Loading)
    val uiState: StateFlow<GithubUiState> = _uiState.asStateFlow()

    fun getRepositories(organization: String) {
        viewModelScope.launch {
            val infoList = githubRepository.getRepositories(organization)
                .map {
                    RepositoryInfo(
                        fullName = it.fullName ?: "",
                        description = it.description ?: "",
                    )
                }
            _uiState.value = if (infoList.isEmpty()) {
                GithubUiState.Empty
            } else {
                GithubUiState.Success(infoList)
            }
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