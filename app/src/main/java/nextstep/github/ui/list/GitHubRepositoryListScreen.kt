package nextstep.github.ui.list

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.R
import nextstep.github.domain.model.Repository
import nextstep.github.ui.component.GitHubRepositoryItem
import nextstep.github.ui.component.SingleTextTopBar
import nextstep.github.ui.theme.GithubTheme

@Composable
internal fun GitHubRepositoryListScreen(
    modifier: Modifier = Modifier,
    viewModel: GitHubRepositoryListViewModel = viewModel(factory = GitHubRepositoryListViewModel.Factory)
) {
    val repositories by viewModel.repositories.collectAsStateWithLifecycle()

    GitHubRepositoryListScreen(
        repositories = repositories,
        modifier = modifier
    )
}

@Composable
internal fun GitHubRepositoryListScreen(
    repositories: List<Repository>,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            SingleTextTopBar(title = stringResource(R.string.repository_list_title))
        }
    ) { innerPadding ->
        RepositoryListContent(
            repositories = repositories,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}

@Composable
private fun RepositoryListContent(
    repositories: List<Repository>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = repositories,
            key = { it.id }
        ) {
            GitHubRepositoryItem(it)
        }
    }
}

@Preview
@Composable
private fun GitHubRepositoryListScreenPreview() {
    GithubTheme {
        GitHubRepositoryListScreen(
            repositories = List(20) {
                Repository(
                    id = it,
                    fullName = "RepositoryFullName/$it",
                    description = "RepositoryDescription -$it"
                )
            },
        )
    }
}
