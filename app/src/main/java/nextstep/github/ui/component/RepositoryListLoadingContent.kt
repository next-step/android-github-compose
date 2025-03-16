package nextstep.github.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.github.ui.theme.GithubTheme

@Composable
fun RepositoryListLoadingContent(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        CircularProgressIndicator(
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RepositoryListLoadingContentPreview() {
    GithubTheme {
        RepositoryListLoadingContent(
            modifier = Modifier.fillMaxSize()
        )
    }
}