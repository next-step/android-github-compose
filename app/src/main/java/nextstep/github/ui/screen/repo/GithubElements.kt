package nextstep.github.ui.screen.repo

import nextstep.github.domain.model.GithubRepositoryModel

data class GithubState(
    val repositories: List<GithubRepositoryModel> = emptyList(),
    val loading: Boolean = true,
    val exception: Throwable? = null,
) {

    companion object {
        val Initial = GithubState(
            repositories = emptyList(),
            loading = true,
            exception = null
        )
    }
}

sealed interface GithubEvent {
    data object Init : GithubEvent
    data object OnRetryClick : GithubEvent
}
