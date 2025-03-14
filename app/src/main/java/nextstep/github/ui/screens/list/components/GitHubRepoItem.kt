package nextstep.github.ui.screens.list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.github.model.GitHubRepo
import nextstep.github.ui.theme.GithubTheme

@Composable
fun GitHubRepoItem(
    gitHubRepo: GitHubRepo,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = gitHubRepo.fullName,
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = gitHubRepo.description.orEmpty(),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GitHubRepoItemPreview() {
    GithubTheme {
        GitHubRepoItem(
            gitHubRepo = GitHubRepo(
                id = 1,
                fullName = "next-step/nextstep-docs",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소",
            ),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GitHubRepoItemPreview_NoDescription() {
    GithubTheme {
        GitHubRepoItem(
            gitHubRepo = GitHubRepo(
                id = 1,
                fullName = "next-step/nextstep-docs",
                description = null,
            ),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
