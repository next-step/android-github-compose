package nextstep.github.ui.screen.github

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.github.core.data.GithubRepositoryInfo
import nextstep.github.ui.screen.github.component.MainTopBar
import nextstep.github.ui.screen.github.list.GithubRepositoryList

@Composable
fun MainScreen(
    githubRepositoryInfoList: List<GithubRepositoryInfo>
) {
    Scaffold(
        topBar = { MainTopBar() }
    )
    { paddingValues ->
        GithubRepositoryList(
            githubRepositoryInfoList = githubRepositoryInfoList,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Preview
@Composable
private fun MainScreenPreview() {
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

    MainScreen(
        githubRepositoryInfoList = githubRepositoryList
    )
}
