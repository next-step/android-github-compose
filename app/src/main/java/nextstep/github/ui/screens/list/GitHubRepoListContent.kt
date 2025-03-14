package nextstep.github.ui.screens.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.github.data.repositories.impls.FakeGithubRepoRepository
import nextstep.github.model.GitHubRepo
import nextstep.github.ui.screens.list.components.GitHubRepoItem
import nextstep.github.ui.theme.GithubTheme

@Composable
fun GitHubRepoListContent(
    repositories: List<GitHubRepo>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier.testTag("repositoryList")
    ) {
        items(key = { item -> item.id }, items = repositories) { repository ->
            GitHubRepoItem(
                gitHubRepo = repository,
                modifier = Modifier.padding(16.dp)
            )
            HorizontalDivider()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GitHubRepoListContentPreview() {
    GithubTheme {
        GitHubRepoListContent(
            repositories = FakeGithubRepoRepository.gitHubRepos,
        )
    }
}
