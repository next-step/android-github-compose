package nextstep.github.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.R
import nextstep.github.data.repository.model.RepositoryEntity
import nextstep.github.ui.RepositoryListViewModel
import nextstep.github.ui.component.RepositoryList
import nextstep.github.ui.model.RepositoryListEvent
import nextstep.github.ui.model.RepositoryListUiState
import nextstep.github.ui.theme.GithubTheme

@Composable
fun RepositoryListScreen(
    modifier: Modifier = Modifier,
    viewModel: RepositoryListViewModel = viewModel(factory = RepositoryListViewModel.Factory)
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val lifecycleOwner = LocalLifecycleOwner.current
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    LaunchedEffect(lifecycleOwner) {
        viewModel.event.collect {
            when(it) {
                is RepositoryListEvent.ShowSnackBar -> {
                    snackbarHostState.showSnackbar(
                        message = context.getString(it.msgRes),
                        actionLabel = context.getString(it.actionLabelRes)
                    )
                }
            }
        }
    }

    RepositoryListScreen(
        uiState = uiState,
        snackbarHostState = snackbarHostState,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryListScreen(
    uiState: RepositoryListUiState,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.repository_list_title),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Snackbar(
                    snackbarData = data,
                    actionColor = MaterialTheme.colorScheme.inversePrimary
                )
            }
        }
    ) { paddingValues ->
        when (uiState) {
            RepositoryListUiState.Empty -> {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.surface),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(R.string.repository_list_empty_content),
                        style = MaterialTheme.typography.headlineSmall,
                    )
                }
            }

            is RepositoryListUiState.Loading -> {
                Box(
                    modifier = modifier
                        .fillMaxSize()
                        .background(color = MaterialTheme.colorScheme.surface),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }

            is RepositoryListUiState.Success -> {
                RepositoryList(
                    repositories = uiState.items,
                    modifier = Modifier.padding(paddingValues)
                )
            }
        }
    }
}

class UiStatePreviewParameterProvider : PreviewParameterProvider<RepositoryListUiState> {
    override val values = sequenceOf(
        RepositoryListUiState.Empty,
        RepositoryListUiState.Loading,
        RepositoryListUiState.Success(
            List(10) {
                RepositoryEntity(
                    id = it.toLong(),
                    fullName = "next-step/nextstep-docs",
                    description = "nextstep 매뉴얼 및 문서를 관리하는 저장소"
                )
            }
        )
    )
}

@Preview
@Composable
private fun RepositoryListScreenPreview(
    @PreviewParameter(UiStatePreviewParameterProvider::class) uiState: RepositoryListUiState
) {
    GithubTheme {
        val snackbarHostState = remember { SnackbarHostState() }

        RepositoryListScreen(
            uiState = uiState,
            snackbarHostState = snackbarHostState,
            modifier = Modifier.fillMaxSize()
        )
    }
}
