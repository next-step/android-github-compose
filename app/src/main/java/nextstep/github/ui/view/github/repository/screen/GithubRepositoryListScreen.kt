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
import nextstep.github.model.GithubRepositoryDto
import nextstep.github.ui.view.github.repository.GithubRepositoryListViewModel

@Composable
fun GithubRepositoryListScreen(
    modifier: Modifier = Modifier,
    viewModel: GithubRepositoryListViewModel = viewModel(factory = GithubRepositoryListViewModel.Factory),
) {
    val items by viewModel.repositories.collectAsStateWithLifecycle()
    val isLoading by viewModel.isLoading.collectAsStateWithLifecycle()
    val isError by viewModel.error.collectAsStateWithLifecycle()
    GithubRepositoryListScreen(
        modifier = modifier,
        items = items,
        isLoading = isLoading,
        isError = isError,
        onRetry = viewModel::retry
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GithubRepositoryListScreen(
    items: List<GithubRepositoryDto>,
    isLoading: Boolean,
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
        GithubRepositoryListSuccessScreen(
            modifier = Modifier.padding(paddingValues),
            items = items,
        )

        if (isLoading) {
            GithubRepositoryListLoadingScreen(
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GithubRepositoryListScreenPreviewSuccess() {
    GithubRepositoryListScreen(
        items = List(10) {
            GithubRepositoryDto(
                fullName = "next-step/nextstep-docs",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소"
            )
        },
        isLoading = false,
        isError = false,
        onRetry = {},
    )
}

@Preview(showBackground = true)
@Composable
private fun GithubRepositoryListScreenPreviewLoading() {
    GithubRepositoryListScreen(
        items = emptyList(),
        isLoading = true,
        isError = false,
        onRetry = {},
    )
}
