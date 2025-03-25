package nextstep.github.ui.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.github.data.repository.model.RepositoryEntity
import nextstep.github.ui.theme.GithubTheme

@Composable
fun RepositoryList(
    repositories: List<RepositoryEntity>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(repositories) { item ->
            RepositoryItem(
                repository = item,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Preview
@Composable
private fun RepositoryListPreview() {
    GithubTheme {
        RepositoryList(
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
