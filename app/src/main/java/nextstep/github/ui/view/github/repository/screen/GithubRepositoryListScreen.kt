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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import nextstep.github.model.GithubRepositoryDto
import nextstep.github.ui.view.github.repository.GithubRepositoryListUiState
import nextstep.github.ui.view.github.repository.GithubRepositoryListViewModel

@Composable
fun GithubRepositoryListScreen(
    modifier: Modifier = Modifier,
    viewModel: GithubRepositoryListViewModel = viewModel(factory = GithubRepositoryListViewModel.Factory),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    GithubRepositoryListScreen(
        modifier = modifier,
        uiState = uiState.value,
        onRetry = viewModel::retry
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GithubRepositoryListScreen(
    uiState: GithubRepositoryListUiState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = SnackbarHostState()
    LaunchedEffect(key1 = uiState) {
        if (uiState == GithubRepositoryListUiState.Error) {
            coroutineScope.launch {
                val result = snackbarHostState.showSnackbar(
                    message = "예상치 못한 오류가 발생했습니다.",
                    actionLabel = "재시도"
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
                Text(text = "NEXTSTEP Repositories")
            })
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
    ) { paddingValues ->
        when (uiState) {
            is GithubRepositoryListUiState.Loading,
            is GithubRepositoryListUiState.Error -> {
                GithubRepositoryListLoadingScreen(
                    modifier = Modifier.padding(paddingValues)
                )
            }

            is GithubRepositoryListUiState.Success -> {
                GithubRepositoryListSuccessScreen(
                    modifier = Modifier.padding(paddingValues),
                    uiState = uiState
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GithubRepositoryListScreenPreviewSuccess() {
    GithubRepositoryListScreen(
        uiState = GithubRepositoryListUiState.Success(
            repositories = List(10) {
                GithubRepositoryDto(
                    fullName = "next-step/nextstep-docs",
                    description = "nextstep 매뉴얼 및 문서를 관리하는 저장소"
                )
            }
        ),
        onRetry = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun GithubRepositoryListScreenPreviewLoading() {
    GithubRepositoryListScreen(
        uiState = GithubRepositoryListUiState.Loading,
        onRetry = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun GithubRepositoryListScreenPreviewError() {
    GithubRepositoryListScreen(
        uiState = GithubRepositoryListUiState.Error,
        onRetry = {},
    )
}
