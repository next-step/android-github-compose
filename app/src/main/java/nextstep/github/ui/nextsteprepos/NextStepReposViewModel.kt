package nextstep.github.ui.nextsteprepos

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import nextstep.github.GithubApplication
import nextstep.github.data.repository.GithubRepoRepository

class NextStepReposViewModel(
    private val githubRepoRepository: GithubRepoRepository
) : ViewModel() {

    private val _uiState: MutableStateFlow<NextStepReposUiState> =
        MutableStateFlow(NextStepReposUiState())
    val uiState: StateFlow<NextStepReposUiState> = _uiState.asStateFlow()

    private val _effect: Channel<NestStepReposEffect> = Channel()
    val effect = _effect.receiveAsFlow()

    init {
        fetchNextStepRepos()
    }

    fun fetchNextStepRepos() = viewModelScope.launch {
        _uiState.value = _uiState.value.copy(uiState = UiState.Loading)
        githubRepoRepository.getRepos("next-step")
            .onSuccess {
                _uiState.value = NextStepReposUiState(
                    uiState = UiState.Success,
                    nextStepRepos = it,
                )
            }.onFailure {
                _effect.send(NestStepReposEffect.ShowError("예상치 못한 오류가 발생했습니다."))
            }
    }

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
