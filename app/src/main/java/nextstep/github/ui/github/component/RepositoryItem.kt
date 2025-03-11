package nextstep.github.ui.github.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.github.R
import nextstep.github.model.Repository
import nextstep.github.ui.theme.GithubTheme

@Composable
fun RepositoryItem(
    item: Repository,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            if (item.isHotRepository()) {
                Text(
                    text = stringResource(R.string.label_hot_repository),
                    modifier = Modifier.align(Alignment.CenterStart),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.align(Alignment.CenterEnd),
            ) {
                Icon(
                    Icons.Default.Star,
                    contentDescription = "star",
                    modifier = Modifier.size(18.dp)
                )
                Text(
                    text = item.stars.toString(),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.scrim
                )
            }
        }

        Text(
            text = item.fullName,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.scrim
        )
        Text(
            text = item.description,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.scrim
        )
    }
}

private class RepositoryProvider : CollectionPreviewParameterProvider<Repository>(
    listOf(
        Repository(
            id = 0,
            fullName = "next-step/nextstep-docs",
            description = "nextstep 매뉴얼 및 문서를 관리하는 저장소",
            stars = 10
        ),
        Repository(
            id = 1,
            fullName = "next-step/nextstep-docs",
            description = "nextstep 매뉴얼 및 문서를 관리하는 저장소",
            stars = 51
        )
    )
)


@Preview(showBackground = true)
@Composable
private fun RepositoryItemPreview(@PreviewParameter(RepositoryProvider::class) item: Repository) {
    GithubTheme {
        RepositoryItem(item)
    }
}