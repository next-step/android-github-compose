package nextstep.github.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.collections.immutable.toPersistentList
import nextstep.github.data.entity.Repository
import nextstep.github.ui.component.RepositoryListContent
import nextstep.github.ui.component.RepositoryListEmptyContent
import nextstep.github.ui.component.RepositoryListLoadingContent
import nextstep.github.ui.component.RepositoryListTopBar
import nextstep.github.ui.model.RepositoryListScreenUiState
import nextstep.github.ui.theme.GithubTheme

@Composable
fun RepositoryListScreen(
    modifier: Modifier = Modifier,
    viewModel: RepositoryListViewModel = viewModel(factory = RepositoryListViewModel.Factory),
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.loadRepositoryList()
    }

    RepositoryListScreen(
        uiState = uiState.value,
        modifier = modifier
    )
}

@Composable
fun RepositoryListScreen(
    uiState: RepositoryListScreenUiState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = { RepositoryListTopBar() },
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
                    Repository(
                        fullName = "nextstep/github",
                        description = "Github Repository for NextStep"
                    )
                }.toPersistentList()
            )
        )
    }
}

@Preview
@Composable
private fun RepositoryListEmptyScreenPreview() {
    GithubTheme {
        RepositoryListScreen(
            uiState = RepositoryListScreenUiState.Loading
        )
    }
}