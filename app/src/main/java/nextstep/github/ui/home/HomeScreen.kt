package nextstep.github.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.R
import nextstep.github.ui.components.GithubTopBar
import nextstep.github.ui.home.model.dummyData
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
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }

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
        LoadingContent(
            empty = when (homeUiState) {
                is HomeUiState.HasRepos -> false
                is HomeUiState.NoRepos -> homeUiState.isLoading
            },
            emptyContent = { FullScreenLoading() },
        ) {
            when (homeUiState) {
                is HomeUiState.NoRepos -> {
                    if (homeUiState.errorMessage.isEmpty()) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = stringResource(id = R.string.empty_repos),
                                style = MaterialTheme.typography.headlineSmall
                            )
                        }
                    }
                }

                is HomeUiState.HasRepos -> {
                    LazyColumn(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        items(homeUiState.githubRepos) { githubRepo ->
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            ) {
                                Text(
                                    text = githubRepo.fullName,
                                    style = MaterialTheme.typography.titleLarge
                                )
                                Text(
                                    text = githubRepo.description,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
        if (homeUiState.errorMessage.isNotEmpty()) {
            val errorMessage = remember(homeUiState) { homeUiState.errorMessage }
            val errorMessageText: String = stringResource(R.string.snack_bar_title)
            val retryMessageText = stringResource(id = R.string.retry)
            LaunchedEffect(errorMessage) {
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


    }
}

@Composable
private fun LoadingContent(
    empty: Boolean,
    emptyContent: @Composable () -> Unit,
    content: @Composable () -> Unit
) {
    if (empty) {
        emptyContent()
    } else {
        content()
    }
}

@Composable
private fun FullScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        CircularProgressIndicator()
    }
}

class HomeScreenProvider : PreviewParameterProvider<HomeUiState> {
    override val values: Sequence<HomeUiState> = sequenceOf(
        HomeUiState.HasRepos(dummyData),
        HomeUiState.NoRepos(errorMessage = "", isLoading = false)
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
