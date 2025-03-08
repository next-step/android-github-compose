package nextstep.github.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import nextstep.github.ui.model.GithubRepositoryModel
import nextstep.github.ui.preview.GithubRepositoryParameterProvider
import nextstep.github.ui.theme.GithubTheme
import nextstep.github.ui.theme.Typography

@Composable
internal fun GithubRepository(
    model: GithubRepositoryModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = model.fullName.orEmpty(),
            style = Typography.titleLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = model.description.orEmpty(),
            style = Typography.bodyMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GithubRepositoryPreview(
    @PreviewParameter(GithubRepositoryParameterProvider::class)
    model: GithubRepositoryModel
) {
    GithubTheme {
        GithubRepository(model)
    }
}