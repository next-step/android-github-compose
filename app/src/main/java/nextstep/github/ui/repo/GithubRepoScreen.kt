package nextstep.github.ui.repo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.R
import nextstep.github.core.model.RepositoryEntity
import nextstep.github.ui.repo.component.EmptyScreen
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

    GithubRepoScreen(
        uiState = uiState,
        modifier = modifier,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun GithubRepoScreen(
    uiState: GithubRepoUiState,
    modifier: Modifier = Modifier,
) {
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
        modifier = modifier,
    ) { innerPadding ->
        when (uiState) {
            GithubRepoUiState.Loading -> {
                LoadingScreen(
                    modifier = Modifier.fillMaxSize(),
                )
            }

            is GithubRepoUiState.Error -> {
                TODO("Not yet implemented")
            }

            GithubRepoUiState.Empty -> {
                EmptyScreen(
                    modifier = Modifier.fillMaxSize(),
                )
            }

            is GithubRepoUiState.Success -> {
                TODO("Not yet implemented")
            }
        }
//        LazyColumn(
//            modifier =
//            Modifier
//                .fillMaxSize()
//                .padding(innerPadding)
//                .testTag("GithubRepoCards"),
//        ) {
//            items(repositories) { item ->
//                GithubRepoCard(
//                    modifier = Modifier.fillMaxWidth(),
//                ) {
//                    GithubRepoContent(
//                        fullName = item.fullName,
//                        description = item.description,
//                    )
//                }
//            }
//        }
    }
}

@Composable
private fun GithubRepoCard(
    modifier: Modifier = Modifier,
    bottomBorderColor: Color = MaterialTheme.colorScheme.outlineVariant,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    tag: @Composable (() -> Unit) = {},
    badge: @Composable (() -> Unit) = {},
    content: @Composable () -> Unit = {},
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier.padding(contentPadding),
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
            ) {
                tag()
                badge()
            }
            content()
        }
        HorizontalDivider(
            color = bottomBorderColor,
            modifier =
                Modifier
                    .fillMaxWidth()
                    .align(alignment = Alignment.TopCenter),
        )
    }
}

@Composable
private fun GithubRepoContent(
    fullName: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = fullName,
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
        )
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
                GithubRepoUiState.Error(errorMessage = "Error"),
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
