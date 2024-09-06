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
import nextstep.github.domain.GetOrganizationReposUseCase
import nextstep.github.domain.model.GithubRepo

sealed interface HomeUiState {

    data object Loading: HomeUiState
    data class HasRepos(val githubRepos: List<GithubRepo>) : HomeUiState
    data object Empty: HomeUiState
    data object Error: HomeUiState

}

class HomeViewModel(
    private val getOrganizationReposUseCase: GetOrganizationReposUseCase
) : ViewModel() {

    private val _homeUiState: MutableStateFlow<HomeUiState> = MutableStateFlow(HomeUiState.Loading)
    val homeUiState = _homeUiState.asStateFlow()

    init {
        fetchRepos("next-step")
    }

    fun fetchRepos(organization: String) {
        _homeUiState.value = HomeUiState.Loading
        viewModelScope.launch {
            getOrganizationReposUseCase(organization)
                .fold(
                    onSuccess = { result ->
                        if (result.isEmpty()) {
                            _homeUiState.value = HomeUiState.Empty
                        } else {
                            _homeUiState.value = HomeUiState.HasRepos(result)
                        }
                    },
                    onFailure = {
                        _homeUiState.value = HomeUiState.Error
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
                HomeViewModel(GetOrganizationReposUseCase(githubRepository))
            }
        }
    }

}
