package nextstep.github.ui.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.github.ui.theme.GithubTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryListTopBar(
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                "NEXTSTEP Repositories",
                style = MaterialTheme.typography.titleLarge,
            )
        },
        modifier = modifier,
    )
}

@Preview
@Composable
private fun RepositoryListTopBarPreview() {
    GithubTheme {
        RepositoryListTopBar()
    }
}