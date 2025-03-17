package nextstep.github.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nextstep.github.GithubApplication
import nextstep.github.data.GithubRepository

class GithubViewModel(
    private val githubRepository: GithubRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<GithubUiState> = MutableStateFlow(GithubUiState.Loading)
    val uiState: StateFlow<GithubUiState> = _uiState.asStateFlow()

    private val _uiEvent : MutableSharedFlow<GithubUiEvent> = MutableSharedFlow()
    val uiEvent : SharedFlow<GithubUiEvent> = _uiEvent.asSharedFlow()

    fun getRepositories(organization: String) {
        viewModelScope.launch {
            runCatching {
                return@runCatching githubRepository.getRepositories(organization)
                    .map {
                        RepositoryInfo(
                            fullName = it.fullName ?: "",
                            description = it.description ?: "",
                        )
                    }
            }
                .onSuccess { infoList ->
                    _uiState.value = if (infoList.isEmpty()) {
                        GithubUiState.Empty
                    } else {
                        GithubUiState.Success(infoList)
                    }
                }
                .onFailure {
                    sendUiEvent(GithubUiEvent.ShowErrorSnackBar)
                }
        }
    }

    fun sendUiEvent(event: GithubUiEvent) {
        viewModelScope.launch {
            _uiEvent.emit(event)
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