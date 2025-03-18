package nextstep.github.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.launch
import nextstep.github.ui.component.RepositoryListContent
import nextstep.github.ui.component.RepositoryListEmptyContent
import nextstep.github.ui.component.RepositoryListErrorContent
import nextstep.github.ui.component.RepositoryListLoadingContent
import nextstep.github.ui.component.RepositoryListTopBar
import nextstep.github.ui.model.RepositoryListScreenSideEffect
import nextstep.github.ui.model.RepositoryListScreenUiState
import nextstep.github.ui.model.RepositoryUiModel
import nextstep.github.ui.theme.GithubTheme

@Composable
fun RepositoryListScreen(
    modifier: Modifier = Modifier,
    viewModel: RepositoryListViewModel = viewModel(factory = RepositoryListViewModel.Factory),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarHostState by remember { mutableStateOf(SnackbarHostState()) }

    LaunchedEffect(Unit) {
        viewModel.loadRepositoryList()
    }

    LaunchedEffect(Unit) {
        viewModel.sideEffect.collect { sideEffect ->
            when (sideEffect) {
                is RepositoryListScreenSideEffect.ShowErrorSnackBar -> {
                    snackBarHostState.showSnackbar(
                        message = "예상치 못한 오류가 발생하였습니다.",
                        actionLabel = "재시도",
                    ).let {
                        if (it == SnackbarResult.ActionPerformed) {
                            viewModel.loadRepositoryList()
                        }
                    }
                }
            }
        }
    }

    RepositoryListScreen(
        uiState = uiState.value,
        modifier = modifier,
        snackBarHostState = snackBarHostState,
    )
}

@Composable
fun RepositoryListScreen(
    uiState: RepositoryListScreenUiState,
    modifier: Modifier = Modifier,
    snackBarHostState: SnackbarHostState = SnackbarHostState(),
) {
    Scaffold(
        topBar = { RepositoryListTopBar() },
        snackbarHost = {
            SnackbarHost(
                hostState = snackBarHostState,
                snackbar = {
                    Snackbar(
                        snackbarData = it,
                        actionColor = Color(0xFFD0BCFF),
                    )
                }
            )
        },
        modifier = modifier,
    ) { paddingValues ->
        when (uiState) {
            is RepositoryListScreenUiState.Loading -> {
                RepositoryListLoadingContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )
            }

            is RepositoryListScreenUiState.Success -> {
                RepositoryListContent(
                    repositoryList = uiState.repositoryList,
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )
            }

            is RepositoryListScreenUiState.Empty -> {
                RepositoryListEmptyContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )
            }

            is RepositoryListScreenUiState.Error -> {
                RepositoryListErrorContent(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                )
            }
        }
    }
}

@Preview
@Composable
private fun RepositoryListScreenPreview() {
    GithubTheme {
        RepositoryListScreen(
            uiState = RepositoryListScreenUiState.Success(
                repositoryList = List(10) {
                    RepositoryUiModel(
                        fullName = "nextstep/github",
                        description = "Github Repository for NextStep",
                        stars = 50,
                        isHot = true,
                    )
                }.toPersistentList()
            )
        )
    }
}

@Preview
@Composable
private fun RepositoryListLoadingScreenPreview() {
    GithubTheme {
        RepositoryListScreen(
            uiState = RepositoryListScreenUiState.Loading
        )
    }
}

@Preview
@Composable
private fun RepositoryListEmptyScreenPreview() {
    GithubTheme {
        RepositoryListScreen(
            uiState = RepositoryListScreenUiState.Empty
        )
    }
}

@SuppressLint("CoroutineCreationDuringComposition")
@Preview
@Composable
private fun RepositoryListErrorScreenPreview() {
    GithubTheme {
        val scope = rememberCoroutineScope()
        val snackBarHostState = SnackbarHostState()

        scope.launch {
            snackBarHostState.showSnackbar(
                message = "예상치 못한 오류가 발생하였습니다.",
                actionLabel = "재시도",
                duration = SnackbarDuration.Indefinite,
            )
        }

        RepositoryListScreen(
            uiState = RepositoryListScreenUiState.Error,
            snackBarHostState = snackBarHostState
        )
    }
}