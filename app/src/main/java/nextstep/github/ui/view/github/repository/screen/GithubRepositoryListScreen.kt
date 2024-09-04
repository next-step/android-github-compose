package nextstep.github.ui.view.github.repository.screen

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import nextstep.github.R
import nextstep.github.ui.model.GithubRepositoryModel
import nextstep.github.ui.view.github.repository.GithubRepositoryListUiState
import nextstep.github.ui.view.github.repository.GithubRepositoryListViewModel

@Composable
fun GithubRepositoryListScreen(
    modifier: Modifier = Modifier,
    viewModel: GithubRepositoryListViewModel = viewModel(factory = GithubRepositoryListViewModel.Factory),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val isError by viewModel.error.collectAsStateWithLifecycle()
    GithubRepositoryListScreen(
        uiState = uiState,
        isError = isError,
        onRetry = viewModel::retry,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GithubRepositoryListScreen(
    uiState: GithubRepositoryListUiState,
    isError: Boolean,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = SnackbarHostState()
    LaunchedEffect(key1 = isError) {
        if (isError) {
            coroutineScope.launch {
                val result = snackbarHostState.showSnackbar(
                    message = context.getString(R.string.error_message),
                    actionLabel = context.getString(R.string.error_retry)
                )

                if (result == SnackbarResult.ActionPerformed) {
                    onRetry()
                }
            }
        }
    }
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = stringResource(R.string.github_repository_title))
            })
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { paddingValues ->
        when (uiState) {
            GithubRepositoryListUiState.Loading -> {
                GithubRepositoryListLoadingScreen(
                    modifier = Modifier.padding(paddingValues)
                )
            }

            GithubRepositoryListUiState.NotFound -> {
                GithubRepositoryListNotFoundScreen(
                    modifier = Modifier.padding(paddingValues)
                )
            }

            is GithubRepositoryListUiState.Found -> {
                GithubRepositoryListFoundScreen(
                    modifier = Modifier.padding(paddingValues),
                    items = uiState.repositories,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GithubRepositoryListScreenPreviewSuccess() {
    GithubRepositoryListScreen(
        uiState = GithubRepositoryListUiState.Found(
            repositories = List(10) {
                GithubRepositoryModel(
                    fullName = "next-step/nextstep-docs",
                    description = "nextstep 매뉴얼 및 문서를 관리하는 저장소",
                    stars = 0,
                    isHot = false,
                )
            }
        ),
        isError = false,
        onRetry = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun GithubRepositoryListScreenPreviewLoading() {
    GithubRepositoryListScreen(
        uiState = GithubRepositoryListUiState.Loading,
        isError = false,
        onRetry = {},
    )
}
