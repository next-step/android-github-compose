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
import nextstep.github.domain.model.GithubRepositoryModel
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

    LaunchedEffect(Unit) {
        viewModel.handleEvent(GithubEvent.Init)
    }

    GithubRepositoryScreen(
        modifier = modifier,
        repositoryItems = state.repositories,
        isLoading = state.loading,
        isError = state.exception != null,
        eventSink = viewModel::handleEvent
    )
}

@Composable
internal fun GithubRepositoryScreen(
    repositoryItems: List<GithubRepositoryModel>,
    isLoading: Boolean,
    isError: Boolean,
    eventSink: (GithubEvent) -> Unit,
    modifier: Modifier = Modifier,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val context = LocalContext.current

    LaunchedEffect(isError) {
        if (isError) {
            val result = snackbarHostState.showSnackbar(
                message = context.getString(R.string.common_not_found_error),
                actionLabel = context.getString(R.string.common_retry),
                duration = SnackbarDuration.Indefinite
            )

            when (result) {
                SnackbarResult.Dismissed -> Unit
                SnackbarResult.ActionPerformed -> {
                    eventSink(GithubEvent.OnRetryClick)
                }
            }
        }
    }

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
    repositoryItems: List<GithubRepositoryModel>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(repositoryItems) {
            GithubRepositoryItem(
                modifier = Modifier.padding(16.dp),
                fullName = it.fullName,
                description = it.description,
                startCount = it.stars,
                isHot = it.isHot
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
private fun ScreenPreview_정상케이스() {
    GithubTheme {
        GithubRepositoryScreen(
            repositoryItems = List(5) { index ->
                GithubRepositoryModel(
                    fullName = "next-step/nextstep-docs",
                    description = "nextstep 매뉴얼 및 문서를 관리하는 저장소",
                    stars = index * 15
                )
            },
            isLoading = false,
            isError = false,
            eventSink = {}
        )
    }
}

@Composable
@Preview
private fun ScreenPreview_로딩() {
    GithubTheme {
        GithubRepositoryScreen(
            repositoryItems = emptyList(),
            isLoading = true,
            isError = false,
            eventSink = {}
        )
    }
}

@Composable
@Preview
private fun ScreenPreview_빈화면() {
    GithubTheme {
        GithubRepositoryScreen(
            repositoryItems = emptyList(),
            isLoading = false,
            isError = false,
            eventSink = {}
        )
    }
}

@Composable
@Preview
private fun ScreenPreview_에러() {
    GithubTheme {
        GithubRepositoryScreen(
            repositoryItems = emptyList(),
            isLoading = false,
            isError = true,
            eventSink = {}
        )
    }
}
