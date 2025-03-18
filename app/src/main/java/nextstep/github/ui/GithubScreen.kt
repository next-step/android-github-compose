package nextstep.github.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import nextstep.github.ui.view.EmptyRepositoryBox
import nextstep.github.ui.view.LoadingBox
import nextstep.github.ui.view.RepositoryList
import nextstep.github.util.Const.DEFAULT_ORGANIZATION

@Composable
fun GithubScreen(
    modifier: Modifier = Modifier,
    viewModel: GithubViewModel = viewModel(factory = GithubViewModel.Factory),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    LaunchedEffect(Unit) {
        scope.launch {
            viewModel.uiEvent.collectLatest { event ->
                when (event) {
                    GithubUiEvent.ShowErrorSnackBar -> {
                        val snackBarResult = snackbarHostState.showSnackbar(
                            message = "예상치 못한 오류가 발생했습니다.",
                            withDismissAction = true,
                            actionLabel = "재시도",
                        )
                        if (snackBarResult == SnackbarResult.ActionPerformed) {
                            viewModel.getRepositories(DEFAULT_ORGANIZATION)
                        }
                    }
                }
            }
        }
        viewModel.getRepositories(DEFAULT_ORGANIZATION)
    }

    GithubScreen(
        uiState = uiState,
        snackbarHostState = snackbarHostState,
        modifier = modifier,
    )
}

@Composable
fun GithubScreen(
    uiState: GithubUiState,
    modifier: Modifier = Modifier,
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
) {
    Scaffold(
        topBar = {
            GithubTopBar()
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        content = { paddingValues ->
            when (uiState) {
                GithubUiState.Empty -> {
                    EmptyRepositoryBox(
                        modifier = modifier
                            .padding(paddingValues)
                            .fillMaxSize(),
                    )
                }

                GithubUiState.Loading -> {
                    LoadingBox(
                        modifier = modifier
                            .padding(paddingValues)
                            .fillMaxSize(),
                    )
                }

                is GithubUiState.Success -> {
                    RepositoryList(
                        repositories = uiState.repositories,
                        modifier = modifier.padding(paddingValues),
                    )
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GithubTopBar(
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "NEXTSTEP Repositories",
                style = MaterialTheme.typography.titleLarge,
            )
        },
        modifier = modifier,
    )
}

private class GithubUiStatePreviewParameterProvider : PreviewParameterProvider<GithubUiState> {
    override val values: Sequence<GithubUiState> = sequenceOf(
        GithubUiState.Loading,
        GithubUiState.Empty,
        GithubUiState.Success(
            listOf(
                RepositoryInfo(
                    fullName = "fullName 1",
                    description = "description 1",
                ),
                RepositoryInfo(
                    fullName = "fullName 2",
                    description = "description 2",
                ),
            ),
        ),
    )
}

@Preview
@Composable
private fun GithubScreenPreview(
    @PreviewParameter(GithubUiStatePreviewParameterProvider::class) uiState: GithubUiState
) {
    GithubScreen(
        uiState = uiState,
        snackbarHostState = remember { SnackbarHostState() },
    )
}