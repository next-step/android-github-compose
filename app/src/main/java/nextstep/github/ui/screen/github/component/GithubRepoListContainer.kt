package nextstep.github.ui.screen.github.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.github.ui.screen.github.RepositoryUiState

@Composable
fun GithubRepoListContainer(
    repositories: List<RepositoryUiState>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(
            items = repositories,
            key = { it.id }
        ) {
            GithubRepoItem(
                fullName = it.fullName,
                description = it.description,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GithubRepoListContainerPreview() {
    val dummyList = List(3) {
        RepositoryUiState(
            id = it,
            fullName = "next-step/nextstep-docs",
            description = "nextstep 매뉴얼 및 문서를 관리하는 저장소",
        )
    }

    GithubRepoListContainer(
        repositories = dummyList
    )
}
