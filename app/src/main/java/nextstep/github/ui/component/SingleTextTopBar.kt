package nextstep.github.ui.component

import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import nextstep.github.R
import nextstep.github.ui.preview.BackgroundPreview
import nextstep.github.ui.theme.GithubTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun SingleTextTopBar(
    title: String,
    modifier: Modifier = Modifier,
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = Color(0xFF1D1B20),
                maxLines = 1,
                textAlign = TextAlign.Center,
            )
        },
        expandedHeight = 56.dp,
        modifier = modifier
    )
}

@BackgroundPreview
@Composable
private fun SingleTextTopBarPreview_longTitle() {
    GithubTheme {
        SingleTextTopBar(
            title = "안녕".repeat(100)
        )
    }
}

@BackgroundPreview
@Composable
private fun SingleTextTopBarPreview() {
    GithubTheme {
        SingleTextTopBar(
            title = stringResource(R.string.repository_list_title)
        )
    }
}
