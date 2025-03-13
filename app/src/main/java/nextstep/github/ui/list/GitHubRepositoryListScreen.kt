package nextstep.github.ui.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
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

    GitHubRepositoryListScreen(
        uiState = uiState,
        modifier = modifier
    )
}

@Composable
internal fun GitHubRepositoryListScreen(
    uiState: GitHubRepositoryListState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = { SingleTextTopBar(title = stringResource(R.string.repository_list_title)) },
    ) { innerPadding ->
        when (uiState) {
            GitHubRepositoryListState.Empty -> {
                RepositoryEmptyContent(modifier = Modifier.padding(innerPadding))
            }

            GitHubRepositoryListState.Loading -> {
                RepositoryLoadingContent(modifier = Modifier.padding(innerPadding))
            }

            is GitHubRepositoryListState.Repositories -> {
                RepositoryListContent(
                    repositories = uiState.list,
                    modifier = Modifier.padding(innerPadding)
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
            GitHubRepositoryListState.Empty,
            GitHubRepositoryListState.Loading,
            GitHubRepositoryListState.Repositories(
                List(20) {
                    Repository(
                        id = it,
                        fullName = "RepositoryFullName/$it",
                        description = "RepositoryDescription -$it"
                    )
                }
            ),
        )
    )
