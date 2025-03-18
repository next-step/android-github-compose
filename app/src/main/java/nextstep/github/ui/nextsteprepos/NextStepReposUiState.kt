package nextstep.github.ui.nextsteprepos

import nextstep.github.model.GithubRepo

data class NextStepReposUiState(
    val isLoading: Boolean = true,
    val nextStepRepos: List<GithubRepo> = emptyList()
)