package nextstep.github.ui.screen.github.list.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.github.core.data.GithubRepositoryInfo

@Composable
fun GithubRepositoryList(
    githubRepositoryInfoList: List<GithubRepositoryInfo>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        items(githubRepositoryInfoList.size) {
            RepositoryColumn(
                repositoryInfo = githubRepositoryInfoList[it],
                modifier = Modifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GithubRepositoryListPreview() {
    val githubRepositoryList = listOf(
        GithubRepositoryInfo(
            fullName = "next-step/nextstep-study",
            description = "코드숨과 함께하는 NextStep"
        ),
        GithubRepositoryInfo(
            fullName = "next-step/nextstep-study",
            description = "코드숨과 함께하는 NextStep"
        ),
        GithubRepositoryInfo(
            fullName = "next-step/nextstep-study",
            description = "코드숨과 함께하는 NextStep"
        ),
        GithubRepositoryInfo(
            fullName = "next-step/nextstep-study",
            description = "코드숨과 함께하는 NextStep"
        )
    )

    GithubRepositoryList(
        githubRepositoryInfoList = githubRepositoryList,
        modifier = Modifier
    )
}
