package nextstep.github.ui.screen.repo

import nextstep.github.domain.model.GithubRepositoryModel

data class GithubState(
    val repositories: List<GithubRepositoryModel> = emptyList(),
    val loading: Boolean = true,
    val isError: Boolean = false,
) {

    companion object {
        val Initial = GithubState(
            repositories = emptyList(),
            loading = true,
            isError = false
        )
    }
}

sealed interface GithubEvent {
    data object Init : GithubEvent
    data object OnRetryClick : GithubEvent
}
