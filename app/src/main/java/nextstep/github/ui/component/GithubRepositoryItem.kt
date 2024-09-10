package nextstep.github.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.github.R
import nextstep.github.ui.theme.GithubTheme

@Composable
fun GithubRepositoryItem(
    fullName: String,
    description: String,
    startCount: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = stringResource(R.string.repository_start_count, fullName, startCount),
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = startCount.toString(),
                style = MaterialTheme.typography.labelLarge
            )
        }
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
            description = repository.second,
            startCount = 100
        )
    }
}
