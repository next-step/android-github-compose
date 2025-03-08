package nextstep.github.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.ui.component.GithubRepositoryList
import nextstep.github.ui.component.GithubRepositoryTopBar
import nextstep.github.ui.theme.GithubTheme

@Composable
internal fun GithubRepositoryScreen(
    viewModel: GithubRepositoryViewModel = viewModel<GithubRepositoryViewModel>(factory = GithubRepositoryViewModel.Factory),
) {
    val githubRepositories by viewModel.githubRepositories.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { GithubRepositoryTopBar() }
    ) { innerPadding ->
        GithubRepositoryList(
            model = githubRepositories,
            modifier = Modifier.padding(innerPadding),
        )
    }
}

@Preview
@Composable
private fun GithubRepositoryScreePreview() {
    GithubTheme {
        GithubRepositoryScreen()
    }
}