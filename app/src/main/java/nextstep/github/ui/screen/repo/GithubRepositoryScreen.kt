package nextstep.github.ui.screen.repo

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.R
import nextstep.github.data.response.RepositoryResponse
import nextstep.github.ui.component.EmptyContent
import nextstep.github.ui.component.GithubRepositoryItem
import nextstep.github.ui.component.LoadingContent
import nextstep.github.ui.theme.GithubTheme

@Composable
fun GithubRepositoryRoute(
    modifier: Modifier = Modifier,
    viewModel: GithubRepositoryViewModel = viewModel(factory = GithubRepositoryViewModel.Factory),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.handleEvent(GithubEvent.Init)
    }

    LaunchedEffect(state.exception) {
        if (state.exception != null) {
            val result = snackbarHostState.showSnackbar(
                message = context.getString(R.string.common_not_found_error),
                actionLabel = context.getString(R.string.common_retry),
                duration = SnackbarDuration.Indefinite
            )

            when (result) {
                SnackbarResult.Dismissed -> Unit
                SnackbarResult.ActionPerformed -> {
                    viewModel.handleEvent(GithubEvent.OnRetryClick)
                }
            }
        }
    }

    GithubRepositoryScreen(
        modifier = modifier,
        repositoryItems = state.repositories,
        isLoading = state.loading,
        snackbarHostState = snackbarHostState,
    )
}

@Composable
internal fun GithubRepositoryScreen(
    modifier: Modifier = Modifier,
    repositoryItems: List<RepositoryResponse>,
    isLoading: Boolean,
    snackbarHostState: SnackbarHostState,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            GithubRepositoryTopAppBar()
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { innerPadding ->
        val innerPaddingModifier = Modifier.padding(innerPadding)
        if (isLoading) {
            LoadingContent(modifier = innerPaddingModifier)
        } else if (repositoryItems.isEmpty()) {
            EmptyContent(modifier = innerPaddingModifier)
        } else {
            RepositoryContent(
                repositoryItems = repositoryItems,
                modifier = innerPaddingModifier,
            )
        }
    }
}

@Composable
private fun RepositoryContent(
    repositoryItems: List<RepositoryResponse>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(repositoryItems) {
            GithubRepositoryItem(
                modifier = Modifier.padding(16.dp),
                fullName = it.fullName,
                description = it.description
            )
        }
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun GithubRepositoryTopAppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.github_repository_top_app_bar),
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleLarge
            )
        }
    )
}

@Composable
@Preview
private fun GithubRepositoryScreenPreview() {
    GithubTheme {
        GithubRepositoryScreen(
            repositoryItems = List(5) {
                RepositoryResponse(
                    fullName = "next-step/nextstep-docs",
                    description = "nextstep 매뉴얼 및 문서를 관리하는 저장소"
                )
            },
            isLoading = false,
            snackbarHostState = SnackbarHostState()
        )
    }
}

@Composable
@Preview
private fun GithubRepositoryLoadingScreenPreview() {
    GithubTheme {
        GithubRepositoryScreen(
            repositoryItems = emptyList(),
            isLoading = true,
            snackbarHostState = SnackbarHostState()
        )
    }
}

@Composable
@Preview
private fun GithubRepositoryEmptyScreenPreview() {
    GithubTheme {
        GithubRepositoryScreen(
            repositoryItems = emptyList(),
            isLoading = false,
            snackbarHostState = SnackbarHostState()
        )
    }
}
