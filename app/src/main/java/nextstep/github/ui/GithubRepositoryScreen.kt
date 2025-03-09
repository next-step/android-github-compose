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
import nextstep.github.ui.theme.GithubTheme

@Composable
internal fun GithubRepositoryScreen(
    viewModel: GithubRepositoryViewModel = viewModel<GithubRepositoryViewModel>(factory = GithubRepositoryViewModel.Factory),
) {
    val repositoryUiState by viewModel.state.repositoryUiState.collectAsStateWithLifecycle()

    val snackBarHostState = remember { SnackbarHostState() }
    var showSnackBar by remember { mutableStateOf(false) }

    Scaffold(
        topBar = { GithubRepositoryTopBar() },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
    ) { innerPadding ->

        when (repositoryUiState) {
            is GithubRepositoryState.RepositoryUiState.Loading -> {
                GithubRepositoryLoading()
            }

            is GithubRepositoryState.RepositoryUiState.Success -> {
                (repositoryUiState as? GithubRepositoryState.RepositoryUiState.Success)?.let { state ->
                    if (state.items.isEmpty()) {
                        GithubRepositoryEmpty()
                    } else {
                        GithubRepositoryList(
                            model = state.items,
                            modifier = Modifier.padding(innerPadding),
                        )
                    }
                }
            }

            else -> {
                /* do nothing **/
            }
        }
    }
}

@Preview
@Composable
private fun GithubRepositoryScreePreview() {
    GithubTheme {
        GithubRepositoryScreen()
    }
}