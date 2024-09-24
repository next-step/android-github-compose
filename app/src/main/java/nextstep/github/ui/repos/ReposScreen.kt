package nextstep.github.ui.repos

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
internal fun ReposScreen(
    reposViewModel: ReposViewModel,
) {
    val uiState by reposViewModel.repos.collectAsStateWithLifecycle()

    ReposScreen(
        uiState = uiState,
        onRetryClick = reposViewModel::searchRepos
    )
}

@Composable
private fun ReposScreen(
    uiState: ReposUiState,
    onRetryClick: () -> Unit,
) {
    when (uiState) {
        is ReposUiState.Loading -> ReposLoadingScreen()
        is ReposUiState.Success -> ReposSuccessScreen(repos = uiState.repos)
        is ReposUiState.Error -> ReposErrorScreen(onRetryClick = onRetryClick)
    }
}