package nextstep.github.ui.repo

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.R
import nextstep.github.core.model.RepositoryEntity
import nextstep.github.ui.repo.component.EmptyScreen
import nextstep.github.ui.repo.component.GithubRepoCards
import nextstep.github.ui.repo.component.LoadingScreen
import nextstep.github.ui.theme.GithubTheme

@Composable
fun GithubRepoRoute(
    modifier: Modifier = Modifier,
    viewModel: GithubRepoViewModel =
        viewModel(
            factory = GithubRepoViewModel.Factory,
        ),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val snackBarHostState = remember { SnackbarHostState() }
    var showErrorMessage by remember { mutableStateOf(false) }
    LaunchedEffect(viewModel.effect) {
        viewModel.effect.collect {
            when (it) {
                is GithubRepoEffect.ShowErrorMessage -> {
                    showErrorMessage = true
                }
            }
        }
    }

    GithubRepoScreen(
        uiState = uiState,
        snackBarHostState = snackBarHostState,
        showErrorMessage = showErrorMessage,
        onShowErrorMessageChanged = { showErrorMessage = false },
        onRetry = viewModel::retry,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun GithubRepoScreen(
    uiState: GithubRepoUiState,
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
    showErrorMessage: Boolean = false,
    onShowErrorMessageChanged: () -> Unit = {},
    onRetry: () -> Unit = {},
) {
    val context = LocalContext.current
    LaunchedEffect(showErrorMessage) {
        if (showErrorMessage) {
            val result =
                snackBarHostState.showSnackbar(
                    message = context.getString(R.string.message_error_unknown),
                    duration = SnackbarDuration.Long,
                    actionLabel = context.getString(R.string.action_retry),
                )
            when (result) {
                SnackbarResult.ActionPerformed -> {
                    onShowErrorMessageChanged()
                    onRetry()
                }

                SnackbarResult.Dismissed -> {
                    onShowErrorMessageChanged()
                }
            }
        }
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.github_repo_top_bar_title),
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
            )
        },
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
                modifier = Modifier.testTag("Snackbar"),
            )
        },
        modifier = modifier,
    ) { innerPadding ->
        when (uiState) {
            GithubRepoUiState.Loading -> {
                LoadingScreen(
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .testTag("LoadingScreen"),
                )
            }

            GithubRepoUiState.Empty -> {
                EmptyScreen(
                    modifier = Modifier.fillMaxSize(),
                )
            }

            is GithubRepoUiState.Success -> {
                GithubRepoCards(
                    uiState = uiState,
                    modifier =
                        Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .testTag("GithubRepoCards"),
                )
            }
        }
    }
}

@Preview
@Composable
private fun GithubRepoScreenPreview(
    @PreviewParameter(GithubRepoScreenProvider::class) uiState: GithubRepoUiState,
) {
    GithubTheme {
        GithubRepoScreen(
            uiState = uiState,
        )
    }
}

private class GithubRepoScreenProvider :
    CollectionPreviewParameterProvider<GithubRepoUiState>(
        collection =
            listOf(
                GithubRepoUiState.Loading,
                GithubRepoUiState.Empty,
                GithubRepoUiState.Success(
                    repositories =
                        listOf(
                            RepositoryEntity(
                                fullName = "nextstep/compose",
                                description = "갓뮤지님의 강의",
                            ),
                            RepositoryEntity(
                                fullName = "nextstep/kotlin-tdd",
                                description = "Jason님의 강의",
                            ),
                        ),
                ),
            ),
    )
