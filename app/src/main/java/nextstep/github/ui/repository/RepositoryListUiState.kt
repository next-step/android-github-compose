package nextstep.github.ui.repository

import nextstep.github.ui.model.UiRepository

sealed interface RepositoryListUiState {

    data object Loading : RepositoryListUiState
    data object Empty : RepositoryListUiState
    data class Success(val repositories: List<UiRepository>) : RepositoryListUiState
    data object Error : RepositoryListUiState

}
