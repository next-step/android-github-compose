package nextstep.github.ui.view.github.repository

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.model.GithubRepositoryDto

@Composable
fun GithubRepositoryListScreen(
    modifier: Modifier = Modifier,
    viewmodel: GithubRepositoryListViewModel = viewModel(factory = GithubRepositoryListViewModel.Factory),
) {
    val items = viewmodel.repositories.collectAsStateWithLifecycle()
    GithubRepositoryListScreen(
        items = items.value,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GithubRepositoryListScreen(
    items: List<GithubRepositoryDto>,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = "NEXTSTEP Repositories")
            })
        },
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(items = items) {
                GithubRepositoryListItem(
                    modifier = Modifier.fillMaxWidth(),
                    fullName = it.fullName,
                    description = it.description,
                )
                HorizontalDivider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GithubRepositoryListScreenPreview() {
    GithubRepositoryListScreen(
        items = List(10) {
            GithubRepositoryDto(
                fullName = "next-step/nextstep-docs",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소"
            )
        }
    )
}
