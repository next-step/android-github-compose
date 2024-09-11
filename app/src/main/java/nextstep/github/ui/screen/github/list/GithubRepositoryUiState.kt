package nextstep.github.ui.screen.github.list

import nextstep.github.domain.entity.RepositoryEntity

sealed class GithubRepositoryUiState {
    data object Loading : GithubRepositoryUiState()
    data class Ready(
        val githubRepositories: List<RepositoryEntity>,
        val isError: Boolean = false
    ) : GithubRepositoryUiState() {
        val isEmpty: Boolean
            get() = githubRepositories.isEmpty()
    }
}
