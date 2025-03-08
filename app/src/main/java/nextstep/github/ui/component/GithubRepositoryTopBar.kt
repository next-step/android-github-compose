package nextstep.github.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.github.R
import nextstep.github.ui.theme.GithubTheme
import nextstep.github.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun GithubRepositoryTopBar(modifier: Modifier = Modifier) {
    TopAppBar(
        title = {
            Title(
                title = stringResource(R.string.github_repositories_top_bar_title)
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface),
    )
}

@Composable
private fun Title(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = Typography.titleLarge,
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp)
            .basicMarquee(),
    )
}

@Preview
@Composable
private fun GithubRepositoryTopBarPreview() {
    GithubTheme {
        GithubRepositoryTopBar()
    }
}

@Preview(showBackground = true)
@Composable
private fun TitlePreview() {
    GithubTheme {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            listOf(
                "타이틀",
                "긴 타이틀 일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십일이삼사오육칠팔구십",
                "",
            ).forEach { title ->
                Title(title)
            }
        }
    }
}