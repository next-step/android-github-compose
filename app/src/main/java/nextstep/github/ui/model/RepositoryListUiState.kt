package nextstep.github.ui.model

import nextstep.github.data.repository.model.RepositoryEntity

sealed interface RepositoryListUiState {
    data class Loading(val error: Boolean): RepositoryListUiState
    data object Empty: RepositoryListUiState
    data class Success(val items: List<RepositoryEntity>): RepositoryListUiState
}
