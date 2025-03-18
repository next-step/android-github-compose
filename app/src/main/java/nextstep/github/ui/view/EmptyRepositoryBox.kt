package nextstep.github.ui.view

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag

@Composable
fun EmptyRepositoryBox(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .testTag("EmptyRepositoryBox"),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = "목록이 비었습니다.",
            style = MaterialTheme.typography.headlineSmall,
        )
    }
}