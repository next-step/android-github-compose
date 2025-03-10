package nextstep.github.ui.github

import nextstep.github.model.Repository

sealed interface GithubUiState {
    data object Loading : GithubUiState
    data object EmptyRepository : GithubUiState
    data class Repositories(val items: List<Repository>) : GithubUiState
}