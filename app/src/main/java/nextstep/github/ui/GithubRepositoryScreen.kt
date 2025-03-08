package nextstep.github.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.github.ui.component.GithubRepositoryList
import nextstep.github.ui.component.GithubRepositoryTopBar
import nextstep.github.ui.theme.GithubTheme

@Composable
internal fun GithubRepositoryScreen(modifier: Modifier = Modifier) {
    Scaffold(
        topBar = { GithubRepositoryTopBar() }
    ) { innerPadding ->
        GithubRepositoryList(
            model = emptyList(),
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