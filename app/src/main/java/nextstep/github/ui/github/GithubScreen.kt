package nextstep.github.ui.github

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.R
import nextstep.github.ext.ToLaunchedEffect
import nextstep.github.model.Repository
import nextstep.github.ui.github.component.EmptyRepository
import nextstep.github.ui.github.component.GithubLoading
import nextstep.github.ui.github.component.GithubTopBar
import nextstep.github.ui.github.component.RepositoryList
import nextstep.github.ui.theme.GithubTheme

@Composable
fun GithubScreen(
    modifier: Modifier = Modifier,
    viewModel: GithubViewModel = viewModel(factory = GithubViewModel.Factory)
) {
    val snackBarHostState = remember { SnackbarHostState() }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val context = LocalContext.current

    viewModel.errorFlow.ToLaunchedEffect {
        snackBarHostState.showSnackbar(
            message = context.getString(R.string.error_github),
            actionLabel = context.getString(R.string.action_label_retry)
        )
    }

    GithubScreen(
        uiState = uiState,
        snackBarHostState = snackBarHostState,
        modifier = modifier
    )
}

@Composable
fun GithubScreen(
    uiState: GithubUiState,
    snackBarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { GithubTopBar() },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        }
    ) { contentPadding ->

        when (uiState) {
            GithubUiState.Loading -> {
                GithubLoading(modifier = Modifier.padding(contentPadding))
            }

            GithubUiState.EmptyRepository -> {
                EmptyRepository(modifier = Modifier.padding(contentPadding))
            }

            is GithubUiState.Repositories -> {
                RepositoryList(
                    modifier = Modifier.padding(contentPadding),
                    items = uiState.items
                )
            }
        }
    }
}

private class GithubUiStateProvider : PreviewParameterProvider<GithubUiState> {
    private val repositories = (1..5).map {
        Repository(
            id = it,
            fullName = "next-step/nextstep-docs${it}",
            description = "nextstep 매뉴얼 및 문서를 관리하는 저장소${it}"
        )
    }
    override val values: Sequence<GithubUiState>
        get() = sequenceOf(
            GithubUiState.Loading,
            GithubUiState.EmptyRepository,
            GithubUiState.Repositories(items = repositories)
        )
}


@Preview(showBackground = true)
@Composable
private fun GithubScreenPreview(@PreviewParameter(GithubUiStateProvider::class) uiState: GithubUiState) {
    val snackBarHostState = remember { SnackbarHostState() }

    GithubTheme {
        GithubScreen(
            uiState = uiState,
            snackBarHostState = snackBarHostState
        )
    }
}