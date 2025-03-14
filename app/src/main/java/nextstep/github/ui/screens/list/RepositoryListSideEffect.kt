package nextstep.github.ui.screens.list

sealed interface RepositoryListSideEffect {
    data object Nothing: RepositoryListSideEffect
    data class ShowError(val throwable: Throwable) : RepositoryListSideEffect
}
