package nextstep.github.ui.list.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.github.domain.model.Repository
import nextstep.github.ui.component.GitHubRepositoryItem
import nextstep.github.ui.theme.GithubTheme

@Composable
internal fun RepositoryListContent(
    repositories: List<Repository>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(
            items = repositories,
            key = { it.id }
        ) {
            GitHubRepositoryItem(it)
        }
    }
}

@Preview
@Composable
private fun RepositoryListContentPreview() {
    GithubTheme {
        RepositoryListContent(
            List(20) {
                Repository(
                    id = it,
                    fullName = "RepositoryFullName/$it",
                    description = "RepositoryDescription -$it"
                )
            }
        )
    }
}
