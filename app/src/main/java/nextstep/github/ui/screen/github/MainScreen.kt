package nextstep.github.ui.screen.github

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.github.core.data.GithubRepositoryInfo
import nextstep.github.ui.screen.github.component.MainTopBar
import nextstep.github.ui.screen.github.list.GithubRepositoryList
import nextstep.github.ui.screen.github.list.GithubRepositoryUiState
import nextstep.github.ui.screen.github.list.LoadingProgress

@Composable
fun MainScreen(
    uiState: GithubRepositoryUiState = GithubRepositoryUiState.Loading
) {
    Scaffold(
        topBar = { MainTopBar() }
    )
    { paddingValues ->
        when (uiState) {
            GithubRepositoryUiState.Loading -> {
                // 로딩 화면
                LoadingProgress(
                    modifier = Modifier.padding(paddingValues)
                )
            }

            GithubRepositoryUiState.Error -> {
                // 에러 화면
            }

            is GithubRepositoryUiState.Success -> {
                GithubRepositoryList(
                    githubRepositoryInfoList = uiState.githubRepositories,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}

@Preview
@Composable
private fun LoadingMainScreenPreview() {
    MainScreen(
        uiState = GithubRepositoryUiState.Loading
    )
}

@Preview
@Composable
private fun SuccessMainScreenPreview() {
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
        uiState = GithubRepositoryUiState.Success(githubRepositoryList)
    )
}
