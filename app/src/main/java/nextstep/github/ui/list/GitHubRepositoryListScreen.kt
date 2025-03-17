package nextstep.github.ui.list

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.collectLatest
import nextstep.github.R
import nextstep.github.domain.model.Repository
import nextstep.github.ui.component.SingleTextTopBar
import nextstep.github.ui.list.component.RepositoryEmptyContent
import nextstep.github.ui.list.component.RepositoryListContent
import nextstep.github.ui.list.component.RepositoryLoadingContent
import nextstep.github.ui.preview.BackgroundPreview
import nextstep.github.ui.theme.GithubTheme

@Composable
internal fun GitHubRepositoryListScreen(
    modifier: Modifier = Modifier,
    viewModel: GitHubRepositoryListViewModel = viewModel(factory = GitHubRepositoryListViewModel.Factory)
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    LaunchedEffect(viewModel) {
        viewModel.errorEvent.collectLatest {
            snackBarHostState.showSnackbar(
                context.getString(R.string.error_message),
                context.getString(R.string.retry)
            ).let {
                if (it == SnackbarResult.ActionPerformed) {
                    viewModel.fetchRepositories()
                }
            }
        }
    }

    GitHubRepositoryListScreen(
        uiState = uiState,
        snackBarHostState = snackBarHostState,
        modifier = modifier
    )
}

@Composable
internal fun GitHubRepositoryListScreen(
    uiState: GitHubRepositoryListState,
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            SingleTextTopBar(title = stringResource(R.string.repository_list_title))
        },
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { innerPadding ->
        when {
            uiState.isLoading -> {
                RepositoryLoadingContent(
                    modifier = Modifier.padding(innerPadding)
                )
            }

            uiState.isEmpty -> {
                RepositoryEmptyContent(
                    modifier = Modifier.padding(innerPadding)
                )
            }

            else -> {
                RepositoryListContent(
                    repositories = uiState.repositories,
                    modifier = Modifier
                        .padding(innerPadding),
                )
            }
        }
    }
}

@BackgroundPreview
@Composable
private fun GitHubRepositoryListScreenPreview(
    @PreviewParameter(GitHubRepositoryListScreenParameterProvider::class)
    uiState: GitHubRepositoryListState,
) {
    GithubTheme {
        GitHubRepositoryListScreen(uiState)
    }
}

private class GitHubRepositoryListScreenParameterProvider :
    CollectionPreviewParameterProvider<GitHubRepositoryListState>(
        listOf(
            GitHubRepositoryListState(isLoading = false, repositories = emptyList()), // Empty
            GitHubRepositoryListState(isLoading = true), // Loading
            GitHubRepositoryListState(
                isLoading = false,
                repositories = List(20) {
                    Repository(
                        id = it,
                        fullName = "RepositoryFullName/$it",
                        description = "RepositoryDescription -$it"
                    )
                }
            ),
        )
    )
