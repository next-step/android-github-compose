package nextstep.github.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.R
import nextstep.github.domain.model.dummyGithubRepo1
import nextstep.github.ui.components.EmptyListScreen
import nextstep.github.ui.components.GithubTopBar
import nextstep.github.ui.theme.GithubTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory),
) {

    val homeUiState by viewModel.homeUiState.collectAsStateWithLifecycle()

    HomeScreen(
        homeUiState = homeUiState,
        onRetry = { viewModel.fetchRepos("next-step") },
        modifier = modifier
    )

}

@Composable
fun HomeScreen(
    homeUiState: HomeUiState,
    modifier: Modifier = Modifier,
    onRetry: () -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val errorMessageText: String = stringResource(R.string.snack_bar_title)
    val retryMessageText = stringResource(id = R.string.retry)

    LaunchedEffect(homeUiState) {
        if (homeUiState is HomeUiState.Error) {
            val result = snackbarHostState
                .showSnackbar(
                    message = errorMessageText,
                    actionLabel = retryMessageText,
                    duration = SnackbarDuration.Indefinite
                )
            if (result == SnackbarResult.ActionPerformed) {
                onRetry()
            }
        }
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        topBar = {
            GithubTopBar(
                title = stringResource(id = R.string.github_top_bar_title)
            )
        },
        modifier = modifier
    ) { innerPadding ->
        when (homeUiState) {
            HomeUiState.Loading -> {
                FullScreenLoading()
            }

            is HomeUiState.HasRepos -> {
                LazyColumn(
                    modifier = Modifier.padding(innerPadding)
                ) {
                    items(homeUiState.githubRepos) { githubRepo ->
                        GithubRepoInfo(githubRepo)
                        HorizontalDivider()
                    }
                }
            }

            HomeUiState.Empty -> {
                EmptyListScreen()
            }

            HomeUiState.Error -> {

            }

        }
    }
}

@Composable
private fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
            .testTag("FullScreenLoading")
    ) {
        CircularProgressIndicator()
    }
}

class HomeScreenProvider : PreviewParameterProvider<HomeUiState> {
    override val values: Sequence<HomeUiState> = sequenceOf(
        HomeUiState.Loading,
        HomeUiState.HasRepos(listOf(dummyGithubRepo1)),
        HomeUiState.Empty,
        HomeUiState.Error
    )
}

@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
private fun HomeScreenPreview(
    @PreviewParameter(HomeScreenProvider::class) homeUiState: HomeUiState
) {
    GithubTheme {
        HomeScreen(
            homeUiState = homeUiState,
            onRetry = { }
        )
    }
}
