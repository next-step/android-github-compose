package nextstep.github.ui.screens.list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.github.R
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
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (gitHubRepo.isHot) {
                Text(
                    text = stringResource(R.string.github_item_hot_repo_label),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Spacer(Modifier.weight(1f))
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = stringResource(R.string.github_item_star),
                modifier = Modifier.size(18.dp)
            )
            Text(text = gitHubRepo.stars.toString(), style = MaterialTheme.typography.labelLarge)
        }
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
                stars = 49,
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
                stars = 49,
            ),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GitHubRepoItemPreview_HotRepo() {
    GithubTheme {
        GitHubRepoItem(
            gitHubRepo = GitHubRepo(
                id = 1,
                fullName = "next-step/nextstep-docs",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소",
                stars = 50,
            ),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
