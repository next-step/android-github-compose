package nextstep.github.ui

sealed interface GithubUiEvent {
    data object ShowErrorSnackBar: GithubUiEvent
}