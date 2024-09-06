package nextstep.github.ui.screen.repo

import nextstep.github.data.response.RepositoryResponse

data class GithubState(
    val repositories: List<RepositoryResponse> = emptyList(),
    val loading: Boolean = true,
    val exception: Throwable? = null,
)

sealed interface GithubEvent {
    data object Init : GithubEvent
    data object OnRetryClick : GithubEvent
}
