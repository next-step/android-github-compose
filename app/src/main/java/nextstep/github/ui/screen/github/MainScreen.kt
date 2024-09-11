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
import nextstep.github.R
import nextstep.github.domain.entity.RepositoryEntity
import nextstep.github.ui.screen.github.list.GithubRepositoryUiState
import nextstep.github.ui.screen.github.list.component.GithubRepositoryEmpty
import nextstep.github.ui.screen.github.list.component.GithubRepositoryList
import nextstep.github.ui.screen.github.list.component.LoadingProgress
import nextstep.github.ui.screen.github.list.component.MainTopBar

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
        onRetry = { viewModel.getRepositories("next-step") }
    )
}

@Composable
fun MainScreen(
    uiState: GithubRepositoryUiState,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onRetry: () -> Unit = {}
) {
    // stateless
    Scaffold(
        topBar = { MainTopBar() },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    )
    { paddingValues ->
        when (uiState) {
            is GithubRepositoryUiState.Loading -> {
                LoadingProgress(
                    modifier = Modifier.padding(paddingValues)
                )
            }

            is GithubRepositoryUiState.Ready -> {
                if (uiState.isError) {
                    val errorMsg = stringResource(id = R.string.text_snackbar_network_error)
                    val actionLabel = stringResource(id = R.string.text_snackbar_action_retry)

                    LaunchedEffect(snackbarHostState) {
                        val snackbarResult = snackbarHostState.showSnackbar(
                            message = errorMsg,
                            actionLabel = actionLabel,
                        )

                        if (snackbarResult == SnackbarResult.ActionPerformed) {
                            onRetry()
                        }
                    }
                }

                if (uiState.isEmpty) {
                    GithubRepositoryEmpty(
                        modifier = Modifier.padding(paddingValues)
                    )
                } else {
                    GithubRepositoryList(
                        repositoryEntityList = uiState.githubRepositories,
                        modifier = Modifier.padding(paddingValues)
                    )
                }
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
        uiState = GithubRepositoryUiState.Ready(
            githubRepositories = emptyList(),
            isError = true
        ),
        snackbarHostState = remember { SnackbarHostState() }
    )
}

@Preview
@Composable
private fun SuccessMainScreenPreview() {
    val githubRepositoryList = listOf(
        RepositoryEntity(
            fullName = "next-step/nextstep-study",
            description = "코드숨과 함께하는 NextStep",
            stars = 2
        ),
        RepositoryEntity(
            fullName = "next-step/nextstep-study",
            description = "코드숨과 함께하는 NextStep",
            stars = 20
        ),
        RepositoryEntity(
            fullName = "next-step/nextstep-study",
            description = "코드숨과 함께하는 NextStep",
            stars = 2
        ),
        RepositoryEntity(
            fullName = "next-step/nextstep-study",
            description = "코드숨과 함께하는 NextStep",
            stars = 3
        )
    )

    MainScreen(
        uiState = GithubRepositoryUiState.Ready(
            githubRepositories = githubRepositoryList,
            isError = false
        ),
        snackbarHostState = remember { SnackbarHostState() }
    )
}
