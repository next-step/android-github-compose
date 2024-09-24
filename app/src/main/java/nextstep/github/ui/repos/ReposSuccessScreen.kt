package nextstep.github.ui.repos

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.github.ui.model.UiGitHubRepoInfo
import nextstep.github.ui.repos.component.GitHubRepoInfoItem

@Composable
internal fun ReposSuccessScreen(
    repos: List<UiGitHubRepoInfo>,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            Box(
                modifier = Modifier
                    .height(56.dp)
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "NEXTSTEP Repositories",
                    style = MaterialTheme.typography.titleLarge
                )
            }
        },
        content = { paddingValues ->
            LazyColumn(modifier = Modifier.padding(paddingValues)) {
                items(repos) { r ->
                    GitHubRepoInfoItem(
                        gitHubRepoInfo = r,
                        modifier = Modifier.fillMaxWidth()
                    )
                    HorizontalDivider(
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.outlineVariant
                    )
                }
            }
        }
    )
}

private data class ReposSuccessScreenPreviewParameter(
    val repos: List<UiGitHubRepoInfo>
)

private class ReposSuccessScreenPreviewParameterProvider :
    PreviewParameterProvider<ReposSuccessScreenPreviewParameter> {
    override val values: Sequence<ReposSuccessScreenPreviewParameter> = sequenceOf(
        ReposSuccessScreenPreviewParameter(
            repos = List(10) {
                UiGitHubRepoInfo(
                    fullName = "next-step/nextstep-docs",
                    description = "NextStep 메뉴얼 및 문서를 관리하는 저장소"
                )
            }
        )
    )
}

@Preview
@Composable
private fun ReposSuccessScreenPreview(
    @PreviewParameter(ReposSuccessScreenPreviewParameterProvider::class)
    parameter: ReposSuccessScreenPreviewParameter
) {
    MaterialTheme {
        ReposSuccessScreen(repos = parameter.repos)
    }
}

