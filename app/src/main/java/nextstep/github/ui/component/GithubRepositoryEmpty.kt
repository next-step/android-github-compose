package nextstep.github.ui.component

import androidx.compose.foundation.background
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
import nextstep.github.ui.theme.Typography

@Composable
internal fun GithubRepositoryEmpty(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.surface),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(R.string.github_repositories_empty),
            style = Typography.headlineSmall,
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun GithubRepositoryEmptyPreview() {
    GithubTheme {
        GithubRepositoryEmpty()
    }
}