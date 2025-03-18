package nextstep.github.ui.screens.list

sealed interface GitHubRepoListSideEffect {
    data object HideError: GitHubRepoListSideEffect
    data class ShowError(val throwable: Throwable) : GitHubRepoListSideEffect
}
