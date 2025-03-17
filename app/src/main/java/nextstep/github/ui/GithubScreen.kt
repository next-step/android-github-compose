package nextstep.github.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.ui.view.RepositoryList
import nextstep.github.util.Const.DEFAULT_ORGANIZATION

@Composable
fun GithubScreen(
    modifier: Modifier = Modifier,
    viewModel: GithubViewModel = viewModel(factory = GithubViewModel.Factory),
) {
    val repositories by viewModel.repositories.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getRepositories(DEFAULT_ORGANIZATION)
    }

    GithubScreen(
        repositories = repositories,
        modifier = modifier,
    )
}

@Composable
private fun GithubScreen(
    repositories: List<RepositoryInfo>,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        topBar = {
            GithubTopBar()
        },
        content = { paddingValues ->
            RepositoryList(
                repositories = repositories,
                modifier = modifier.padding(paddingValues),
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun GithubTopBar(
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "NEXTSTEP Repositories",
                style = MaterialTheme.typography.titleLarge,
            )
        },
        modifier = modifier,
    )
}


@Preview
@Composable
private fun GithubScreenPreview() {
    val repositories = listOf(
        RepositoryInfo(
            fullName = "fullName 1",
            description = "description 1",
        ),
        RepositoryInfo(
            fullName = "fullName 2",
            description = "description 2",
        ),
        RepositoryInfo(
            fullName = "fullName 3",
            description = "description 3",
        ),
        RepositoryInfo(
            fullName = "fullName 4",
            description = "description 4",
        ),
    )

    GithubScreen(
        repositories = repositories
    )
}
