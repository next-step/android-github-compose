package nextstep.github.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.github.ui.theme.GithubTheme

@Composable
internal fun GithubRepositoryLoading(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator()
    }
}


@Preview(showBackground = true)
@Composable
private fun GithubRepositoryLoadingPreview() {
    GithubTheme {
        GithubRepositoryLoading()
    }
}