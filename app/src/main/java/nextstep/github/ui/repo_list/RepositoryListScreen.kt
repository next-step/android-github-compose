package nextstep.github.ui.repo_list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.data.entity.RepositoryEntity

@Composable
fun RepositoryListScreen(
    modifier: Modifier = Modifier,
    viewModel: RepositoryListViewModel = viewModel(factory = RepositoryListViewModel.Factory),
) {
    val repositories by viewModel.repositories.collectAsStateWithLifecycle()

    RepositoryListScreen(
        modifier = modifier,
        repositories = repositories
    )
}

@Composable
fun RepositoryListScreen(
    repositories: List<RepositoryEntity>,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            RepositoryListCenterAlignedTopAppBar(title = "NEXTSTEP Repositories")
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = modifier.padding(innerPadding),
        ) {
            items(repositories) { repository ->
                RepositoryItem(repositoryEntity = repository)

                if (repositories.last() != repository) {
                    HorizontalDivider(color = colorScheme.outlineVariant)
                }
            }
        }
    }
}

@Preview
@Composable
private fun RepoListScreenPreview() {
    val repositories = List(10) {
        RepositoryEntity(
            fullName = "full name",
            description = "description"
        )
    }

    RepositoryListScreen(
        repositories = repositories
    )
}
