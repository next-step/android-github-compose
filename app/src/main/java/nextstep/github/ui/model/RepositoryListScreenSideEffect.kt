package nextstep.github.ui.model

sealed interface RepositoryListScreenSideEffect {
    data object ShowErrorSnackBar : RepositoryListScreenSideEffect
}