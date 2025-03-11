package nextstep.github.ui.github.repository.list

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.github.R
import nextstep.github.ui.component.SingleTextTopBar
import nextstep.github.ui.theme.GithubTheme

@Composable
internal fun GitHubRepositoryListScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            SingleTextTopBar(title = stringResource(R.string.repository_list_title))
        }
    ) { innerPadding ->

    }
}


@Preview
@Composable
private fun GitHubRepositoryListScreenPreview() {
    GithubTheme {
        GitHubRepositoryListScreen()
    }
}
