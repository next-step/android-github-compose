package nextstep.github.ui.repository

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.model.RepositoryEntity
import nextstep.github.ui.repository.component.RepositoryListItem
import nextstep.github.ui.repository.component.RepositoryListTopBar
import nextstep.github.ui.theme.GithubTheme

@Composable
fun RepositoryListScreen(
    modifier: Modifier = Modifier,
    viewModel: RepositoryListViewModel = viewModel(factory = RepositoryListViewModel.Factory),
) {
    val repositories by viewModel.repositories.collectAsStateWithLifecycle()

    RepositoryListScreen(
        items = repositories,
        modifier = modifier,
    )
}

@Composable
private fun RepositoryListScreen(
    modifier: Modifier = Modifier,
    items: List<RepositoryEntity>,
) {
    Scaffold(topBar = { RepositoryListTopBar() }) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding),
        ) {
            LazyColumn(
                modifier = modifier
                    .fillMaxSize()
                    .background(color = MaterialTheme.colorScheme.surface),
            ) {
                items(items) { item ->
                    RepositoryListItem(
                        item = item,
                        modifier = Modifier.fillMaxWidth(),
                    )
                    HorizontalDivider()
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
            items = listOf(
                RepositoryEntity(
                    fullName = "nextstep/nextstep-docs",
                    description = "nextstep-docs description",
                ),
                RepositoryEntity(
                    fullName = "nextstep/java-racingcar",
                    description = "java-racingcar description",
                ),
            ),
        )
    }
}
