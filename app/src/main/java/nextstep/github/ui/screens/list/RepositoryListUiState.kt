package nextstep.github.ui.screens.list

import nextstep.github.model.GitHubRepo

sealed interface RepositoryListUiState {
    data object Loading : RepositoryListUiState
    data object Empty : RepositoryListUiState
    data class Success(val repositories: List<GitHubRepo>) : RepositoryListUiState
}
