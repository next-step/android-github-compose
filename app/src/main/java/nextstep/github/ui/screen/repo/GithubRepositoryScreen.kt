package nextstep.github.ui.screen.repo

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.R
import nextstep.github.data.response.RepositoryResponse
import nextstep.github.ui.component.GithubRepositoryItem
import nextstep.github.ui.component.LoadingSection
import nextstep.github.ui.theme.GithubTheme

@Composable
fun GithubRepositoryRoute(
    modifier: Modifier = Modifier,
    viewModel: GithubRepositoryViewModel = viewModel(factory = GithubRepositoryViewModel.Factory),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    SideEffect {
        viewModel.handleEvent(GithubEvent.Init)
    }

    GithubRepositoryScreen(
        modifier = modifier,
        repositoryItems = state.repositories,
        isLoading = state.loading
    )
}

@Composable
private fun GithubRepositoryScreen(
    modifier: Modifier = Modifier,
    repositoryItems: List<RepositoryResponse>,
    isLoading: Boolean,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            GithubRepositoryTopAppBar()
        }
    ) { innerPadding ->
        if (isLoading) {
            LoadingSection(modifier = Modifier.padding(innerPadding))
        } else {
            LazyColumn(
                modifier = Modifier.padding(innerPadding)
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
            isLoading = false
        )
    }
}

@Composable
@Preview
private fun GithubRepositoryLoadingScreenPreview() {
    GithubTheme {
        GithubRepositoryScreen(
            repositoryItems = emptyList(),
            isLoading = true
        )
    }
}
