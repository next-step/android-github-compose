package nextstep.github.ui

sealed class GithubUiState {
    data object Loading : GithubUiState()
    data object Empty : GithubUiState()
    data object Error : GithubUiState()
    data class Success(val repositories: List<RepositoryInfo>) : GithubUiState()
}

data class RepositoryInfo(
    val fullName: String,
    val description: String,
)