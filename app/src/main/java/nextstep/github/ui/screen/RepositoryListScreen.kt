package nextstep.github.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.ui.component.RepositoryItem
import nextstep.github.ui.model.RepositoryModel
import nextstep.github.ui.theme.GithubTheme

@Composable
internal fun RepositoryListRouteScreen(
    modifier: Modifier = Modifier,
    viewModel: RepositoryListViewModel = viewModel(
        factory = RepositoryListViewModel.Factory
    )
) {
    val repositories by viewModel.repositories.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getRepositories()
    }

    RepositoryListScreen(
        modifier = modifier,
        repositories = repositories
    )
}


@Composable
internal fun RepositoryListScreen(
    repositories: List<RepositoryModel>,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            RepositoryListTopBar()
        }
    ) { innerPadding ->
        RepositoryListLazyColumn(
            modifier = Modifier.padding(innerPadding),
            repositories = repositories
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun RepositoryListTopBar(
    modifier: Modifier = Modifier
) {
    CenterAlignedTopAppBar(
        modifier = modifier,
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.White
        ),
        title = {
            Text(
                text = "NEXTSTEP Repositories",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    )
}

@Composable
internal fun RepositoryListLazyColumn(
    repositories: List<RepositoryModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(repositories){ repository ->
            RepositoryItem(repositoryModel = repository)
        }
    }
}

@Preview(showBackground = true, name = "RepositoryListScreen")
@Composable
private fun Preview1() {
    GithubTheme {
        RepositoryListScreen(
            repositories = listOf(
                RepositoryModel(
                    fullName = "NextStep1",
                    description = "NextStepDesc1"
                ),
                RepositoryModel(
                    fullName = "NextStep2",
                    description = "NextStepDesc2"
                ),
                RepositoryModel(
                    fullName = "NextStep3",
                    description = "NextStepDesc3"
                ),
                RepositoryModel(
                    fullName = "NextStep4",
                    description = "NextStepDesc4"
                )
            )
        )
    }
}

