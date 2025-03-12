package nextstep.github.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.ui.component.GithubRepositoryEmpty
import nextstep.github.ui.component.GithubRepositoryList
import nextstep.github.ui.component.GithubRepositoryLoading
import nextstep.github.ui.component.GithubRepositorySnackBar
import nextstep.github.ui.component.GithubRepositoryTopBar
import nextstep.github.ui.component.dummyList
import nextstep.github.ui.theme.GithubTheme

@Composable
internal fun GithubRepositoryScreen(
    viewModel: GithubRepositoryViewModel = viewModel<GithubRepositoryViewModel>(factory = GithubRepositoryViewModel.Factory),
) {
    val repositoryUiState by viewModel.state.repositoryUiState.collectAsStateWithLifecycle()

    GithubRepositoryScreen(
        repositoryUiState = repositoryUiState,
        showSnackBar = repositoryUiState is GithubRepositoryState.RepositoryUiState.Error,
        onClickSnackBarRetry = { viewModel.loadRepositories() }
    )
}

@Composable
private fun GithubRepositoryScreen(
    repositoryUiState: GithubRepositoryState.RepositoryUiState,
    showSnackBar: Boolean,
    onClickSnackBarRetry: () -> Unit,
) {

    val snackBarHostState = remember { SnackbarHostState() }

    Scaffold(
        topBar = { GithubRepositoryTopBar() },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)

        when (repositoryUiState) {
            is GithubRepositoryState.RepositoryUiState.Loading -> GithubRepositoryLoading(modifier)
            is GithubRepositoryState.RepositoryUiState.Empty -> GithubRepositoryEmpty(modifier)
            is GithubRepositoryState.RepositoryUiState.Data -> GithubRepositoryList(
                model = repositoryUiState.items,
                modifier = modifier
            )

            else -> {
                /** do nothing */
            }
        }
    }

    if (showSnackBar) {
        GithubRepositorySnackBar(
            snackBarHostState = snackBarHostState,
            onRetryAction = {
                onClickSnackBarRetry()
            }
        )
    }
}

@Composable
private fun GithubRepositoryScreen(
    repositoryUiState: GithubRepositoryState.RepositoryUiState,
) {
    GithubRepositoryScreen(
        repositoryUiState = repositoryUiState,
        showSnackBar = false,
        onClickSnackBarRetry = {},
    )
}

@Preview
@Composable
private fun GithubRepositoryScreeLoadingPreview() {
    GithubTheme {
        GithubRepositoryScreen(repositoryUiState = GithubRepositoryState.RepositoryUiState.Loading)
    }
}

@Preview
@Composable
private fun GithubRepositoryScreeEmptyPreview() {
    GithubTheme {
        GithubRepositoryScreen(repositoryUiState = GithubRepositoryState.RepositoryUiState.Empty)
    }
}

@Preview
@Composable
private fun GithubRepositoryScreeRepositoryPreview() {
    GithubTheme {
        GithubRepositoryScreen(
            repositoryUiState = GithubRepositoryState.RepositoryUiState.Data(
                dummyList()
            )
        )
    }
}

@Preview
@Composable
private fun GithubRepositoryScreeErrorPreview() {
    var repositoryState by remember {
        mutableStateOf<GithubRepositoryState.RepositoryUiState>(
            GithubRepositoryState.RepositoryUiState.Error()
        )
    }

    GithubTheme {
        GithubRepositoryScreen(
            repositoryUiState = repositoryState,
            showSnackBar = repositoryState is GithubRepositoryState.RepositoryUiState.Error,
            onClickSnackBarRetry = {
                repositoryState = GithubRepositoryState.RepositoryUiState.Empty
            },
        )
    }
}