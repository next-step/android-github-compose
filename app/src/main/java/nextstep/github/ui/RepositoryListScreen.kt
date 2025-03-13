package nextstep.github.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.data.entity.Repository
import nextstep.github.ui.component.RepositoryItem
import nextstep.github.ui.component.RepositoryListTopBar
import nextstep.github.ui.theme.GithubTheme

@Composable
fun RepositoryListScreen(
    modifier: Modifier = Modifier,
    viewModel: RepositoryListViewModel = viewModel(factory = RepositoryListViewModel.Factory),
) {
    val repositoryList = viewModel.repositoryList.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadRepositoryList()
    }

    RepositoryListScreen(
        repositoryList = repositoryList.value,
        modifier = modifier
    )
}

@Composable
fun RepositoryListScreen(
    repositoryList: List<Repository>,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { RepositoryListTopBar() },
        modifier = modifier,
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {
            items(repositoryList.size) { index ->
                RepositoryItem(
                    repository = repositoryList[index],
                    modifier = Modifier.fillMaxWidth()
                )
                if (index < repositoryList.lastIndex) {
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.outlineVariant,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun RepositoryListScreenPreview() {
    GithubTheme {
        RepositoryListScreen(
            repositoryList = listOf(
                Repository(
                    fullName = "nextstep/github",
                    description = "Github Repository for NextStep"
                ),
                Repository(
                    fullName = "nextstep/nextstep-docs",
                    description = "NextStep Docs Repository"
                ),
                Repository(
                    fullName = "nextstep/github",
                    description = "Github Repository for NextStep"
                ),
                Repository(
                    fullName = "nextstep/nextstep-docs",
                    description = "NextStep Docs Repository"
                ),
                Repository(
                    fullName = "nextstep/github",
                    description = "Github Repository for NextStep"
                ),
                Repository(
                    fullName = "nextstep/nextstep-docs",
                    description = "NextStep Docs Repository"
                ),
                Repository(
                    fullName = "nextstep/github",
                    description = "Github Repository for NextStep"
                ),
                Repository(
                    fullName = "nextstep/nextstep-docs",
                    description = "NextStep Docs Repository"
                ),
                Repository(
                    fullName = "nextstep/github",
                    description = "Github Repository for NextStep"
                ),
                Repository(
                    fullName = "nextstep/nextstep-docs",
                    description = "NextStep Docs Repository"
                ),
            ),
        )
    }
}