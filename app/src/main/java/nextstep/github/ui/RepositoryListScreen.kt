package nextstep.github.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.data.entity.Repository
import nextstep.github.ui.component.RepositoryListTopBar
import nextstep.github.ui.theme.GithubTheme

@Composable
fun RepositoryListScreen(
    modifier: Modifier = Modifier,
    viewModel: RepositoryListViewModel = viewModel(factory = RepositoryListViewModel.Factory),
) {
    RepositoryListScreen(
        repositoryList = emptyList(),
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

        }
    }
}

@Preview
@Composable
private fun RepositoryListScreenPreview() {
    GithubTheme {
        RepositoryListScreen(
            repositoryList = emptyList(),
        )
    }
}