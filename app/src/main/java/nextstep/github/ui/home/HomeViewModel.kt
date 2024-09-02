package nextstep.github.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import nextstep.github.App
import nextstep.github.data.repo.GithubRepository
import nextstep.github.ui.home.model.GithubRepo
import java.util.UUID

sealed interface HomeUiState {

    val isLoading: Boolean
    val errorMessage: String

    data class HasRepos(
        val githubRepos: List<GithubRepo>,
        override val isLoading: Boolean = false,
        override val errorMessage: String = "",
    ) : HomeUiState

    data class NoRepos(
        override val isLoading: Boolean = true,
        override val errorMessage: String = "",
    ) : HomeUiState

}

class HomeViewModel(
    private val githubRepository: GithubRepository
) : ViewModel() {

    private val _homeUiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.NoRepos())
    val homeUiState = _homeUiState.asStateFlow()

    init {
        fetchRepos("next-step")
    }

    fun fetchRepos(organization: String) {
        viewModelScope.launch {
            githubRepository.fetchRepos(organization)
                .fold(
                    onSuccess = { result ->
                        if (result.isEmpty()) {
                            _homeUiState.value = HomeUiState.NoRepos(errorMessage = "", isLoading = false)
                        } else {
                            _homeUiState.value = HomeUiState.HasRepos(GithubRepo.fromResponse(result))
                        }
                    },
                    onFailure = { error ->
                        _homeUiState.value = HomeUiState.NoRepos(
                            errorMessage = (UUID.randomUUID().toString() + error.message),
                            isLoading = true
                        )
                    }
                )
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val githubRepository = (this[APPLICATION_KEY] as App)
                    .appContainer
                    .githubRepository
                HomeViewModel(githubRepository)
            }
        }
    }

}
