package nextstep.github.ui.view.github.repository

import nextstep.github.ui.model.GithubRepositoryModel

sealed interface GithubRepositoryListUiState {
    data object Loading : GithubRepositoryListUiState
    data object Empty : GithubRepositoryListUiState
    data class Repositories(val repositories: List<GithubRepositoryModel>) : GithubRepositoryListUiState
}
