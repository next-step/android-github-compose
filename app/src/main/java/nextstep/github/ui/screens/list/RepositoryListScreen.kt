package nextstep.github.ui.screens.list

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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.collectLatest
import nextstep.github.R
import nextstep.github.ui.components.GithubTopBar

@Composable
fun RepositoryListScreen(
    modifier: Modifier = Modifier,
    viewModel: RepositoryListViewModel = viewModel(factory = RepositoryListViewModel.Factory),
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    val snackBarHostState = remember { SnackbarHostState() }

    val message = stringResource(R.string.snack_bar_unexpected_error)
    val actionLabel = stringResource(R.string.snack_bar_action_label_retry)

    LaunchedEffect(viewModel) {
        viewModel.sideEffect.collectLatest { effect ->
            when(effect) {
                is RepositoryListSideEffect.ShowError -> {
                    when (snackBarHostState.showSnackbar(
                        message = message,
                        actionLabel = actionLabel,
                    )) {
                        SnackbarResult.Dismissed -> Unit
                        SnackbarResult.ActionPerformed -> {
                            viewModel.observeRepositories()
                        }
                    }
                }

                RepositoryListSideEffect.Nothing -> Unit
            }
        }
    }

    RepositoryListScreen(
        state = state,
        snackBarHostState = snackBarHostState,
        modifier = modifier,
    )
}

@Composable
fun RepositoryListScreen(
    state: RepositoryListUiState,
    snackBarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = { GithubTopBar(title = stringResource(R.string.repository_list_top_bat_title)) },
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        modifier = modifier,
    ) { paddingValues ->
        when (state) {
            RepositoryListUiState.Empty ->
                EmptyRepositoryContent(modifier = Modifier.padding(paddingValues))

            RepositoryListUiState.Loading ->
                LoadingContent(modifier = Modifier.padding(paddingValues))

            is RepositoryListUiState.Success ->
                RepositoryListSuccessScreen(
                    state = state,
                    modifier = Modifier.padding(paddingValues),
                )
        }
    }
}
