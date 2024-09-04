package nextstep.github.ui.github

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.model.GithubRepo
import nextstep.github.ui.github.component.GithubRepoItem
import nextstep.github.ui.theme.GithubTheme

@Composable
fun GithubRepositoryListScreen(
    modifier: Modifier = Modifier,
    viewModel: GithubRepoViewModel = viewModel(factory = GithubRepoViewModel.Factory),
) {
    val repositoryList by viewModel.githubRepositories.collectAsStateWithLifecycle()
    GithubRepositoryListScreen(repositories = repositoryList)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GithubRepositoryListScreen(
    repositories: List<GithubRepo>,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("NEXTSTEP Repositories") }
            )
        }
    ) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(it),
        ) {
            items(repositories) {repo ->
                GithubRepoItem(githubRepo = repo)
                HorizontalDivider()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GithubRepositoryListScreenPreview() {
    GithubTheme {
        GithubRepositoryListScreen(
            repositories = listOf(
                GithubRepo(1, "NextStep/Test", "테스트 저장소"),
                GithubRepo(2, "NextStep/Test2", "테스트 저장소2"),
                GithubRepo(3, "NextStep/Test3", "테스트 저장소3"),
                GithubRepo(4, "NextStep/Test4", "테스트 저장소4"),
                GithubRepo(5, "NextStep/Test5", "테스트 저장소5"),
                GithubRepo(6, "NextStep/Test6", "테스트 저장소6"),
            )
        )
    }
}