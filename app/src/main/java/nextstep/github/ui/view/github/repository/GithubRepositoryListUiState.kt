package nextstep.github.ui.view.github.repository

import nextstep.github.model.GithubRepositoryDto

sealed interface GithubRepositoryListUiState {
    data object Loading: GithubRepositoryListUiState
    data class Success(val repositories: List<GithubRepositoryDto>): GithubRepositoryListUiState
    data object Error: GithubRepositoryListUiState
}
