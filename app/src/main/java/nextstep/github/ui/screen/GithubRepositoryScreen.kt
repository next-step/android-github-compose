package nextstep.github.ui.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.github.R
import nextstep.github.ui.theme.GithubTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GithubRepositoryScreen(
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            GithubRepositoryTopAppBar()
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {

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
        GithubRepositoryScreen()
    }
}
