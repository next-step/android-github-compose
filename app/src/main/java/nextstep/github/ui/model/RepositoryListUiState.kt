package nextstep.github.ui.model

import nextstep.github.data.repository.model.RepositoryEntity

sealed interface RepositoryListUiState {
    data object Loading : RepositoryListUiState
    data object Empty: RepositoryListUiState
    data class Success(val items: List<RepositoryEntity>): RepositoryListUiState
}
