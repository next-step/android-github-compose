package nextstep.github.ui.view.github.repository

import nextstep.github.ui.model.GithubRepositoryModel

sealed interface GithubRepositoryListUiState {
    data object Loading : GithubRepositoryListUiState
    data object NotFound : GithubRepositoryListUiState
    data class Found(val repositories: List<GithubRepositoryModel>) : GithubRepositoryListUiState
}
