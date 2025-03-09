package nextstep.github.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import nextstep.github.ui.model.GithubRepositoryModel
import nextstep.github.ui.preview.GithubRepositoryItemParameterProvider
import nextstep.github.ui.theme.GithubTheme
import nextstep.github.ui.theme.Typography

@Composable
internal fun GithubRepositoryItem(
    model: GithubRepositoryModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.surface)
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
private fun GithubRepositoryItemPreview(
    @PreviewParameter(GithubRepositoryItemParameterProvider::class)
    model: GithubRepositoryModel
) {
    GithubTheme {
        GithubRepositoryItem(model)
    }
}