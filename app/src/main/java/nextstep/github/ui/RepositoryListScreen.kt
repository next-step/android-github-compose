package nextstep.github.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.data.entity.Repository

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
        topBar = {},
        modifier = modifier,
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier.padding(paddingValues)
        ) {

        }
    }
}