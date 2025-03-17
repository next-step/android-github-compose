package nextstep.github.ui.list

import nextstep.github.domain.model.Repository

data class GitHubRepositoryListState(
    val isLoading: Boolean = true,
    val repositories: List<Repository> = emptyList(),
) {
    val isEmpty = !isLoading && repositories.isEmpty()
}
