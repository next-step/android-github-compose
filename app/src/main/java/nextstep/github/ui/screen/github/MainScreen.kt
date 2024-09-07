package nextstep.github.ui.screen.github

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.github.R
import nextstep.github.core.data.GithubRepositoryInfo
import nextstep.github.ui.screen.github.component.MainTopBar
import nextstep.github.ui.screen.github.list.GithubRepositoryUiState
import nextstep.github.ui.screen.github.list.component.ErrorSnackbar
import nextstep.github.ui.screen.github.list.component.GithubRepositoryEmpty
import nextstep.github.ui.screen.github.list.component.GithubRepositoryList
import nextstep.github.ui.screen.github.list.component.LoadingProgress

@Composable
fun MainScreen(
    uiState: GithubRepositoryUiState = GithubRepositoryUiState.Loading,
    snackbarHostState: SnackbarHostState,
    onClickSnackBar: () -> Unit = {}
) {
    Scaffold(
        topBar = { MainTopBar() }
    )
    { paddingValues ->
        when (uiState) {
            is GithubRepositoryUiState.Loading -> {
                // 로딩 화면
                LoadingProgress(
                    modifier = Modifier.padding(paddingValues)
                )
            }

            is GithubRepositoryUiState.Error -> {
                // 에러 화면
                LoadingProgress(
                    modifier = Modifier.padding(paddingValues),
                    snackBar = {
                        ErrorSnackbar(
                            errorMessage = stringResource(id = R.string.text_snackbar_network_error),
                            actionString = stringResource(id = R.string.text_snackbar_action_retry),
                            snackbarHostState = snackbarHostState,
                            modifier = Modifier.padding(paddingValues),
                            onClickAction = { onClickSnackBar() }
                        )
                    }
                )
            }

            is GithubRepositoryUiState.Empty -> {
                GithubRepositoryEmpty(
                    modifier = Modifier.padding(paddingValues)
                )
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
        uiState = GithubRepositoryUiState.Loading,
        snackbarHostState = remember { SnackbarHostState() }
    )
}

@Preview
@Composable
private fun EmptyMainScreenPreview() {
    MainScreen(
        uiState = GithubRepositoryUiState.Empty,
        snackbarHostState = remember { SnackbarHostState() }
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
        uiState = GithubRepositoryUiState.Success(githubRepositoryList),
        snackbarHostState = remember { SnackbarHostState() }
    )
}
