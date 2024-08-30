package nextstep.github.ui.repo

import nextstep.github.core.model.NextStepRepository

sealed interface GithubRepoUiState {
    data object Loading : GithubRepoUiState

    data object Empty : GithubRepoUiState

    data class Success(
        val repositories: List<NextStepRepository>,
    ) : GithubRepoUiState
}
