package nextstep.github.ui.screen.github

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.GithubViewModel
import nextstep.github.R
import nextstep.github.core.data.GithubRepositoryInfo
import nextstep.github.ui.screen.github.component.MainTopBar
import nextstep.github.ui.screen.github.list.GithubRepositoryUiState
import nextstep.github.ui.screen.github.list.component.GithubRepositoryEmpty
import nextstep.github.ui.screen.github.list.component.GithubRepositoryList
import nextstep.github.ui.screen.github.list.component.LoadingProgress

@Composable
fun MainScreen(
    viewModel: GithubViewModel = viewModel(),
) {
    // stateful
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    MainScreen(
        uiState = uiState,
        snackbarHostState = snackbarHostState,
        onClickSnackBar = { viewModel.getRepositories("next-step") }
    )
}

@Composable
fun MainScreen(
    uiState: GithubRepositoryUiState = GithubRepositoryUiState.Loading,
    snackbarHostState: SnackbarHostState,
    onClickSnackBar: () -> Unit = {}
) {
    // stateless
    Scaffold(
        topBar = { MainTopBar() },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
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
                val errorMsg = stringResource(id = R.string.text_snackbar_network_error)
                val actionLabel = stringResource(id = R.string.text_snackbar_action_retry)

                LaunchedEffect(snackbarHostState) {
                    val snackbarResult = snackbarHostState.showSnackbar(
                        message = errorMsg,
                        actionLabel = actionLabel,
                    )

                    if (snackbarResult == SnackbarResult.ActionPerformed) {
                        onClickSnackBar()
                    }
                }
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
