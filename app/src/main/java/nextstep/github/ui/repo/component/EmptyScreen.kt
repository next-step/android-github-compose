package nextstep.github.ui.repo.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import nextstep.github.R
import nextstep.github.ui.theme.GithubTheme

@Composable
internal fun EmptyScreen(modifier: Modifier = Modifier) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier,
    ) {
        // Empty screen
        Text(
            text = stringResource(id = R.string.title_empty_screen),
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun EmptyScreenPreview() {
    GithubTheme {
        EmptyScreen(modifier = Modifier.fillMaxSize())
    }
}
