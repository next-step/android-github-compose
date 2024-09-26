package nextstep.github.ui.github

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import nextstep.github.R
import nextstep.github.model.GithubRepo
import nextstep.github.ui.github.component.GithubRepoLoading
import nextstep.github.ui.github.component.GithubRepoList
import nextstep.github.ui.theme.GithubTheme

@Composable
fun GithubRepositoryListScreen(
    modifier: Modifier = Modifier,
    viewModel: GithubRepoViewModel = viewModel(factory = GithubRepoViewModel.Factory),
) {
    val uiState by viewModel.githubUiState.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val snackBarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(true) {
        viewModel.errorFlow.collect {
            coroutineScope.launch {
                val snackBarResult = snackBarHostState.showSnackbar(
                    message = context.getString(R.string.error_message),
                    actionLabel = context.getString(R.string.error_retry)
                )
                if (snackBarResult == SnackbarResult.ActionPerformed) viewModel.retryGithubRepo()
            }
        }
    }
    GithubRepositoryListScreen(
        uiState = uiState,
        snackBarHostState = snackBarHostState,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GithubRepositoryListScreen(
    uiState: GithubRepoUiState,
    snackBarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.top_bar_title)) }
            )
        },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState)}
    ) {
        if (uiState.isLoading) {
            GithubRepoLoading(modifier = Modifier.padding(it))
        }
        GithubRepoList(
            modifier = Modifier.padding(it),
            repositories = uiState.repositories
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GithubRepositoryListScreenPreview() {
    GithubTheme {
        GithubRepositoryListScreen(
            uiState = GithubRepoUiState(
                isLoading = false,
                repositories = listOf(
                    GithubRepo(1, "NextStep/Test", "테스트 저장소"),
                    GithubRepo(2, "NextStep/Test2", "테스트 저장소2"),
                    GithubRepo(3, "NextStep/Test3", "테스트 저장소3"),
                    GithubRepo(4, "NextStep/Test4", "테스트 저장소4"),
                    GithubRepo(5, "NextStep/Test5", "테스트 저장소5"),
                    GithubRepo(6, "NextStep/Test6", "테스트 저장소6"),
                ),
            ),
            snackBarHostState = SnackbarHostState()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GithubRepositoryListScreenErrorPreview() {
    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember { SnackbarHostState() }
    LaunchedEffect(Unit) {
        coroutineScope.launch {
            snackBarHostState.showSnackbar(
                message = "에러 메세지",
                actionLabel = "재시도"
            )
        }
    }
    GithubTheme {
        GithubRepositoryListScreen(
            uiState = GithubRepoUiState(isLoading = false),
            snackBarHostState = snackBarHostState
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GithubRepositoryListScreenLoadingPreview() {
    GithubTheme {
        GithubRepositoryListScreen(
            uiState = GithubRepoUiState(isLoading = true),
            snackBarHostState = SnackbarHostState()
        )
    }
}