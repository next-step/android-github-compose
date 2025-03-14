package nextstep.github.ui.list

import nextstep.github.domain.model.Repository

sealed interface GitHubRepositoryListState {
    data object Empty : GitHubRepositoryListState
    data object Loading : GitHubRepositoryListState
    data class Repositories(
        val list: List<Repository>,
    ) : GitHubRepositoryListState
}
