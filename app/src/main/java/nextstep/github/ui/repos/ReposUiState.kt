package nextstep.github.ui.repos

import nextstep.github.ui.model.UiGitHubRepoInfo

internal sealed interface ReposUiState {
    data object Loading : ReposUiState
    data class Success(val repos: List<UiGitHubRepoInfo>) : ReposUiState
    data class Error(val throwable: Throwable) : ReposUiState
}