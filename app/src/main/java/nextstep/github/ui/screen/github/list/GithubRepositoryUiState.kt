package nextstep.github.ui.screen.github.list

import nextstep.github.core.data.GithubRepositoryInfo

sealed class GithubRepositoryUiState {

    data object Error : GithubRepositoryUiState()

    data object Loading : GithubRepositoryUiState()

    data object Empty : GithubRepositoryUiState()

    data class Success(val githubRepositories: List<GithubRepositoryInfo>) :
        GithubRepositoryUiState()
}
