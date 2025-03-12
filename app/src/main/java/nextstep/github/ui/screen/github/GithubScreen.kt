package nextstep.github.ui.screen.github

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.R
import nextstep.github.ui.screen.component.CenteredContent
import nextstep.github.ui.screen.github.component.GithubRepoListContainer
import nextstep.github.ui.theme.topAppBarContainer
import nextstep.github.ui.uistate.UiState

@Composable
fun GithubScreen(
    modifier: Modifier = Modifier,
    viewModel: GithubViewModel = viewModel(factory = GithubViewModel.Companion.Factory),
) {
    val repositoryUiState by viewModel.repositoryUiState.collectAsStateWithLifecycle()

    GithubScreen(
        repositoryUiState = repositoryUiState,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GithubScreen(
    repositoryUiState: UiState<List<RepositoryUiState>>,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_bar_title),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.W400
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.topAppBarContainer
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            when (repositoryUiState) {
                is UiState.Loading -> {
                    CenteredContent {
                        CircularProgressIndicator()
                    }
                }

                is UiState.Empty -> {
                    CenteredContent {
                        Text(
                            text = "No repositories",
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }

                is UiState.Failure -> {
                    CenteredContent {
                        Text(
                            text = repositoryUiState.meesage,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }

                is UiState.Success -> {
                    GithubRepoListContainer(
                        repositories = repositoryUiState.data,
                        modifier = modifier
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GithubScreenSuccessPreview() {
    val uiState = UiState.Success(
        data = List(10) {
            RepositoryUiState(
                id = it,
                fullName = "next-step/nextstep-docs",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소",
            )
        }
    )

    GithubScreen(
        repositoryUiState = uiState
    )
}

@Preview(showBackground = true)
@Composable
private fun GithubScreenEmptyPreview() {
    GithubScreen(
        repositoryUiState = UiState.Empty
    )
}

@Preview(showBackground = true)
@Composable
private fun GithubScreenLoadingPreview() {
    GithubScreen(
        repositoryUiState = UiState.Loading
    )
}

@Preview(showBackground = true)
@Composable
private fun GithubScreenFailurePreview() {
    GithubScreen(
        repositoryUiState = UiState.Failure("Error message")
    )
}
