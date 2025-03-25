package nextstep.github.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.github.data.repository.model.RepositoryEntity
import nextstep.github.ui.component.RepositoryList
import nextstep.github.ui.theme.GithubTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryListScreen(
    repositories: List<RepositoryEntity>,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "NEXTSTEP Repositories",
                        style = MaterialTheme.typography.titleLarge
                    )
                },
            )
        }
    ) { paddingValues ->
        RepositoryList(
            repositories = repositories,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Preview
@Composable
private fun RepositoryListScreenPreview() {
    GithubTheme {
        RepositoryListScreen(
            repositories = List(10) {
                RepositoryEntity(
                    fullName = "next-step/nextstep-docs",
                    description = "nextstep 매뉴얼 및 문서를 관리하는 저장소"
                )
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}
