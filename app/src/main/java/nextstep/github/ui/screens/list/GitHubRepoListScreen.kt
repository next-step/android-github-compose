package nextstep.github.ui.screens.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.collectLatest
import nextstep.github.R
import nextstep.github.ui.components.GithubTopBar
import nextstep.github.ui.screens.list.components.EmptyGitHubRepoContent
import nextstep.github.ui.screens.list.components.GitHubRepoListContent
import nextstep.github.ui.screens.list.components.LoadingContent

@Composable
fun GitHubRepoListScreen(
    modifier: Modifier = Modifier,
    viewModel: GitHubRepoListViewModel = viewModel(factory = GitHubRepoListViewModel.Factory),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    val snackBarHostState = remember { SnackbarHostState() }

    val message = stringResource(R.string.snack_bar_unexpected_error)
    val actionLabel = stringResource(R.string.snack_bar_action_label_retry)

    LaunchedEffect(viewModel) {
        viewModel.sideEffect.collectLatest { effect ->
            when(effect) {
                is GitHubRepoListSideEffect.ShowError -> {
                    when (snackBarHostState.showSnackbar(
                        message = message,
                        actionLabel = actionLabel,
                    )) {
                        SnackbarResult.Dismissed -> Unit
                        SnackbarResult.ActionPerformed -> {
                            viewModel.observeRepositories()
                        }
                    }
                }

                GitHubRepoListSideEffect.Nothing -> Unit
            }
        }
    }

    GitHubRepoListScreen(
        state = state,
        snackBarHostState = snackBarHostState,
        modifier = modifier,
    )
}

@Composable
fun GitHubRepoListScreen(
    state: GitHubRepoListUiState,
    snackBarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = { GithubTopBar(title = stringResource(R.string.github_repo_list_top_bat_title)) },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        modifier = modifier,
    ) { paddingValues ->
        when (state) {
            GitHubRepoListUiState.Empty ->
                EmptyGitHubRepoContent(modifier = Modifier.padding(paddingValues))

            GitHubRepoListUiState.Loading ->
                LoadingContent(modifier = Modifier.padding(paddingValues))

            is GitHubRepoListUiState.Success ->
                GitHubRepoListContent(
                    repositories = state.repositories,
                    modifier = Modifier.padding(paddingValues),
                )
        }
    }
}
