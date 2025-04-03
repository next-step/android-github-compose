package nextstep.github.ui.model

import nextstep.github.domain.model.Repository

sealed interface RepositoryListUiState {
    data object Loading : RepositoryListUiState
    data object Empty: RepositoryListUiState
    data class Success(val items: List<Repository>): RepositoryListUiState
}
