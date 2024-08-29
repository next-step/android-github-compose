package nextstep.github.ui.repo

import nextstep.github.core.model.RepositoryEntity

sealed interface GithubRepoUiState {
    data object Loading : GithubRepoUiState

    data class Error(
        val errorMessage: String?,
    ) : GithubRepoUiState

    data object Empty : GithubRepoUiState

    data class Success(
        val repositories: List<RepositoryEntity>,
    ) : GithubRepoUiState
}