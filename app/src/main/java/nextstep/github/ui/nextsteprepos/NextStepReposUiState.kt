package nextstep.github.ui.nextsteprepos

import nextstep.github.model.GithubRepo

data class NextStepReposUiState(
    val uiState: UiState = UiState.Success,
    val nextStepRepos: List<GithubRepo> = emptyList()
) {
    val isEmpty: Boolean get() = nextStepRepos.isEmpty()
}

sealed interface UiState {
    data object Loading : UiState
    data object Success : UiState
    data class Error(val message: String) : UiState
}
