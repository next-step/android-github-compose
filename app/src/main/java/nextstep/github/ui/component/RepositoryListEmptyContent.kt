package nextstep.github.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RepositoryListEmptyContent(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Text(
            text = "목록이 비었습니다.",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RepositoryListEmptyContentPreview(modifier: Modifier = Modifier) {
    RepositoryListEmptyContent(modifier = modifier.fillMaxSize())
}