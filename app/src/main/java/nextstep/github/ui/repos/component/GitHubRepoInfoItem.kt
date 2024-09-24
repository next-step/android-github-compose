package nextstep.github.ui.repos.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.github.ui.model.UiGitHubRepoInfo

@Composable
internal fun GitHubRepoInfoItem(
    gitHubRepoInfo: UiGitHubRepoInfo,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        Text(
            text = gitHubRepoInfo.fullName,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = gitHubRepoInfo.description,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview
@Composable
private fun GitHubRepoInfoItemPreview() {
    GitHubRepoInfoItem(
        gitHubRepoInfo = UiGitHubRepoInfo(
            fullName = "next-step/nextstep-docs",
            description = "NextStep 메뉴얼 및 문서를 관리하는 저장소"
        )
    )
}