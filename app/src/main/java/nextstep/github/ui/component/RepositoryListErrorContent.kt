package nextstep.github.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.github.ui.theme.GithubTheme

@Composable
fun RepositoryListErrorContent(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Text(
            text = "에러가 발생하였습니다.",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.Center),
        )
    }
}

@Preview
@Composable
private fun RepositoryListErrorContentPreview() {
    GithubTheme {
        RepositoryListErrorContent()
    }
}