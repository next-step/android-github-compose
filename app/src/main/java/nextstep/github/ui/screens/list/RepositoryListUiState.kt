package nextstep.github.ui.screens.list

import nextstep.github.model.Repository

sealed interface RepositoryListUiState {
    data object Loading : RepositoryListUiState
    data object Error : RepositoryListUiState
    data object Empty : RepositoryListUiState
    data class Success(val repositories: List<Repository>) : RepositoryListUiState
}
