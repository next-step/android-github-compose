package nextstep.github.ui.screens.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.github.R
import nextstep.github.ui.components.GithubTopBar
import nextstep.github.ui.theme.GithubTheme

@Composable
fun RepositoryListEmptyScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { GithubTopBar(title = stringResource(R.string.repository_list_top_bat_title)) },
        modifier = modifier,
    ) {
        Box(
            modifier = Modifier.padding(it).fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(R.string.repository_list_empty),
                style = MaterialTheme.typography.headlineSmall,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RepositoryListEmptyScreenPreview() {
    GithubTheme {
        RepositoryListEmptyScreen(
            modifier = Modifier.fillMaxSize()
        )
    }
}
