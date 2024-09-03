package nextstep.github.ui.screen.repo

import nextstep.github.data.response.RepositoryResponse

data class GithubState(
    val repositories: List<RepositoryResponse> = emptyList(),
)

sealed interface GithubEvent {
    data object Init : GithubEvent
}