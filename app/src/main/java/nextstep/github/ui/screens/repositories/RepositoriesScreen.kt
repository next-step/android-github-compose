package nextstep.github.ui.screens.repositories

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.github.R
import nextstep.github.model.Repository
import nextstep.github.ui.components.GithubTopBar
import nextstep.github.ui.screens.repositories.components.RepositoryItem
import nextstep.github.ui.theme.GithubTheme

@Composable
fun RepositoriesScreen(
    repositories: List<Repository>,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = { GithubTopBar(title = stringResource(R.string.repositories_top_bat_title)) },
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.padding(it)
        ) {
            items(repositories) { repository ->
                RepositoryItem(
                    repository = repository,
                    modifier = Modifier.padding(16.dp)
                )
                HorizontalDivider()
            }
        }
    }
}

@Preview
@Composable
private fun RepositoriesScreenPreview() {
    GithubTheme {
        RepositoriesScreen(
            repositories = List(10) {
                Repository(
                    id = it.toLong(),
                    fullName = "next-step/nextstep-docs-$it",
                    description = if (it % 2 == 0) "nextstep 매뉴얼 및 문서를 관리하는 저장소" else null,
                )
            }
        )
    }
}
