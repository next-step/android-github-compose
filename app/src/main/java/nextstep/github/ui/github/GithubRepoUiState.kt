package nextstep.github.ui.github

import nextstep.github.model.GithubRepo

data class GithubRepoUiState (
    val isLoading: Boolean = false,
    val repositories: List<GithubRepo> = emptyList(),
)

