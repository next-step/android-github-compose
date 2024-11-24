package nextstep.github.model

sealed class LoadState {
    data object Loading : LoadState()
    data object Success : LoadState()
    data object Empty : LoadState()
    data object Error : LoadState()
}
