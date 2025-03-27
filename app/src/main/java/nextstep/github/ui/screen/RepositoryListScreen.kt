package nextstep.github.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.R
import nextstep.github.data.repository.model.RepositoryEntity
import nextstep.github.ui.RepositoryListViewModel
import nextstep.github.ui.component.RepositoryList
import nextstep.github.ui.theme.GithubTheme

@Composable
fun RepositoryListScreen(
    modifier: Modifier = Modifier,
    viewModel: RepositoryListViewModel = viewModel(factory = RepositoryListViewModel.Factory)
) {
    val repositories = viewModel.repositories.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.fetchRepositories()
    }

    RepositoryListScreen(
        repositories = repositories.value,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryListScreen(
    repositories: List<RepositoryEntity>,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.repository_list_title),
                        style = MaterialTheme.typography.titleLarge
                    )
                },
            )
        }
    ) { paddingValues ->
        RepositoryList(
            repositories = repositories,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Preview
@Composable
private fun RepositoryListScreenPreview() {
    GithubTheme {
        RepositoryListScreen(
            repositories = List(10) {
                RepositoryEntity(
                    id = it.toLong(),
                    fullName = "next-step/nextstep-docs",
                    description = "nextstep 매뉴얼 및 문서를 관리하는 저장소"
                )
            },
            modifier = Modifier.fillMaxSize()
        )
    }
}
