package nextstep.github.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.github.ui.theme.GithubTheme

@Composable
fun GithubRepositoryItem(
    fullName: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Text(
            text = fullName,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurface,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

private class GithubRepositoryItemProvider : PreviewParameterProvider<Pair<String, String>> {
    override val values: Sequence<Pair<String, String>> = sequenceOf(
        "next-step/nextstep-docs" to "nextstep 매뉴얼 및 문서를 관리하는 저장소",
        "next-step/nextstep-docs" to "nextstep 매뉴얼 및 문서를 관리하는 저장소 nextstep 매뉴얼 및 문서를 관리하는 저장소 ",
    )
}

@Preview(showBackground = true)
@Composable
private fun GithubRepositoryItemPreview(
    @PreviewParameter(GithubRepositoryItemProvider::class) repository: Pair<String, String>,
) {
    GithubTheme {
        GithubRepositoryItem(
            fullName = repository.first,
            description = repository.second
        )
    }
}
