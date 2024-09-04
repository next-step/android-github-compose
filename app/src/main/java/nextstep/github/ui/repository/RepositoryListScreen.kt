package nextstep.github.ui.repository

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.R
import nextstep.github.model.RepositoryEntity
import nextstep.github.ui.model.UiRepository
import nextstep.github.ui.repository.component.EmptyRepositoryListContent
import nextstep.github.ui.repository.component.RepositoryListTopBar
import nextstep.github.ui.repository.component.SuccessRepositoryListContent
import nextstep.github.ui.theme.GithubTheme

@Composable
fun RepositoryListScreen(
    modifier: Modifier = Modifier,
    viewModel: RepositoryListViewModel = viewModel(factory = RepositoryListViewModel.Factory),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    RepositoryListScreen(
        uiState = uiState,
        onClickRetry = { viewModel.fetchRepositories() },
        modifier = modifier,
    )
}

@Composable
private fun RepositoryListScreen(
    modifier: Modifier = Modifier,
    uiState: RepositoryListUiState,
    onClickRetry: () -> Unit,
) {
    val context = LocalContext.current
    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(uiState) {
        if (uiState is RepositoryListUiState.Error) {
            val result = snackbarHostState.showSnackbar(
                message = context.getString(R.string.unknown_error_occurred),
                actionLabel = context.getString(R.string.retry),
            )

            if (result == SnackbarResult.ActionPerformed) {
                onClickRetry()
            }
        }
    }

    Scaffold(
        topBar = { RepositoryListTopBar() },
        snackbarHost = { SnackbarHost(snackbarHostState) },
    ) { innerPadding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(color = MaterialTheme.colorScheme.surface),
        ) {
            when (val uiState = uiState) {
                RepositoryListUiState.Loading,
                RepositoryListUiState.Error -> CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                )

                RepositoryListUiState.Empty -> EmptyRepositoryListContent()
                is RepositoryListUiState.Success -> SuccessRepositoryListContent(uiState = uiState)
            }
        }
    }
}

@Preview
@Composable
private fun RepositoryListScreenPreview(
    @PreviewParameter(
        RepositoryListScreenPreviewParameterProvider::class
    ) param: RepositoryListUiState,
) {
    GithubTheme {
        RepositoryListScreen(
            uiState = param,
            onClickRetry = {},
        )
    }
}

private class RepositoryListScreenPreviewParameterProvider :
    PreviewParameterProvider<RepositoryListUiState> {
    override val values: Sequence<RepositoryListUiState> = sequenceOf(
        RepositoryListUiState.Loading,
        RepositoryListUiState.Error,
        RepositoryListUiState.Empty,
        RepositoryListUiState.Success(
            listOf(
                UiRepository(
                    fullName = "nextstep/nextstep-docs",
                    description = "nextstep-docs description",
                    stars = 100,
                ),
                UiRepository(
                    fullName = "nextstep/java-racingcar",
                    description = "java-racingcar description",
                    stars = 20,
                ),
            )
        ),
    )

}
