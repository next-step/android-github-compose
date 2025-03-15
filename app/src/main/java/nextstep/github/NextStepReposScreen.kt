package nextstep.github

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.model.GithubRepo
import nextstep.github.ui.theme.GithubTheme

@Composable
fun NextStepReposScreen(
    modifier: Modifier = Modifier,
    viewModel: GithubViewModel = viewModel()
) {
    val nextStepRepos by viewModel.nextStepRepos.collectAsStateWithLifecycle()

    NextStepReposScreen(
        nextStepRepos = nextStepRepos,
        modifier = modifier
    )
}

@Composable
private fun NextStepReposScreen(
    nextStepRepos: List<GithubRepo>,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            NextStepRepoTopBar()
        }) { innerPadding ->
        LazyColumn(modifier = modifier.padding(innerPadding)) {
            items(
                items = nextStepRepos,
                key = { item -> item.fullName }) { githubRepo ->
                NextStepRepoItem(githubRepo = githubRepo)
                HorizontalDivider()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NextStepRepoTopBar(modifier: Modifier = Modifier) {
    Column {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.nextstep_repos_top_bar_title),
                    style = MaterialTheme.typography.titleLarge
                )
            },
            modifier = modifier,
        )
        HorizontalDivider()
    }
}

@Composable
private fun NextStepRepoItem(
    githubRepo: GithubRepo,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(text = githubRepo.fullName, style = MaterialTheme.typography.titleLarge)
        Text(text = githubRepo.description, style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview(showBackground = true)
@Composable
private fun NextStepReposScreenPreview() {
    GithubTheme {
        NextStepReposScreen(
            nextStepRepos = List(20) { it ->
                GithubRepo(
                    fullName = "next-step/nextstep-docs-$it",
                    description = "nextstep 매뉴얼 및 문서를 관리하는 저장소"
                )
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun NextStepRepoItemPreview() {
    GithubTheme {
        NextStepRepoItem(
            githubRepo = GithubRepo(
                fullName = "next-step/nextstep-docs",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소"
            )
        )
    }
}
