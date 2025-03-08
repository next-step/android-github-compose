package nextstep.github.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.ui.component.GithubRepositoryEmpty
import nextstep.github.ui.component.GithubRepositoryList
import nextstep.github.ui.component.GithubRepositoryLoading
import nextstep.github.ui.component.GithubRepositoryTopBar
import nextstep.github.ui.theme.GithubTheme

@Composable
internal fun GithubRepositoryScreen(
    viewModel: GithubRepositoryViewModel = viewModel<GithubRepositoryViewModel>(factory = GithubRepositoryViewModel.Factory),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = { GithubRepositoryTopBar() }
    ) { innerPadding ->

        when (state.loadState) {
            is GithubRepositoryState.LoadState.Loading -> {
                GithubRepositoryLoading()
            }

            is GithubRepositoryState.LoadState.Success -> {
                if (state.repositories.isEmpty()) {
                    GithubRepositoryEmpty()
                } else {
                    GithubRepositoryList(
                        model = state.repositories,
                        modifier = Modifier.padding(innerPadding),
                    )
                }
            }

            else -> {
                /* do nothing **/
            }
        }
    }
}

@Preview
@Composable
private fun GithubRepositoryScreePreview() {
    GithubTheme {
        GithubRepositoryScreen()
    }
}