package nextstep.github.ui.repository

import nextstep.github.model.RepositoryEntity

sealed interface RepositoryListUiState {

    data object Loading : RepositoryListUiState
    data object Empty: RepositoryListUiState
    data class Success(val repositories: List<RepositoryEntity>) : RepositoryListUiState
    data object Error : RepositoryListUiState

}
