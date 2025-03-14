package nextstep.github.ui.uistate

sealed class UiState<out T> {
    data object Empty : UiState<Nothing>()
    data object Loading : UiState<Nothing>()
    data class Success<T>(val data: T) : UiState<T>()
    data class Failure(val meesage: String) : UiState<Nothing>()
}
