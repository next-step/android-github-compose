package nextstep.github.ui.model

import kotlinx.collections.immutable.PersistentList

sealed interface RepositoryListScreenUiState{

    data object Empty: RepositoryListScreenUiState

    data class Success(
        val repositoryList: PersistentList<RepositoryUiModel>
    ): RepositoryListScreenUiState

    data object Loading: RepositoryListScreenUiState

    data object Error: RepositoryListScreenUiState
}