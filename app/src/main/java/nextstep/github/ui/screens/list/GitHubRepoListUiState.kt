package nextstep.github.ui.screens.list

import nextstep.github.model.GitHubRepo

sealed interface GitHubRepoListUiState {
    data object Loading : GitHubRepoListUiState
    data object Empty : GitHubRepoListUiState
    data class Success(val repositories: List<GitHubRepo>) : GitHubRepoListUiState
}
