package nextstep.github.ui.screens.list

sealed interface GitHubRepoListSideEffect {
    data object Nothing: GitHubRepoListSideEffect
    data class ShowError(val throwable: Throwable) : GitHubRepoListSideEffect
}
