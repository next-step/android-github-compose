package nextstep.github.ui.model

import kotlinx.collections.immutable.PersistentList
import nextstep.github.data.entity.Repository

sealed interface RepositoryListScreenUiState{

    data class Success(
        val repositoryList: PersistentList<Repository>
    ): RepositoryListScreenUiState

    data object Loading: RepositoryListScreenUiState
}