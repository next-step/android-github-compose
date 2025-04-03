package nextstep.github.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.github.R
import nextstep.github.data.repository.model.RepositoryEntity
import nextstep.github.ui.theme.GithubTheme
import nextstep.github.util.orZero

@Composable
fun RepositoryItem(
    repository: RepositoryEntity,
    modifier: Modifier = Modifier
) {
    RepositoryItem(
        fullName = repository.fullName.orEmpty(),
        description = repository.description.orEmpty(),
        stars = repository.stars.orZero(),
        modifier = modifier
    )
}

@Composable
fun RepositoryItem(
    fullName: String,
    description: String,
    stars: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                if (stars >= 50) {
                    Text(
                        text = stringResource(R.string.repository_item_hot),
                        style = MaterialTheme.typography.labelLarge,
                        color = MaterialTheme.colorScheme.primary
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                StarCount(
                    count = stars,
                )
            }
            Text(
                text = fullName,
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
        }
        HorizontalDivider()
    }
}

@Composable
fun StarCount(
    count: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            modifier = Modifier.size(18.dp),
            imageVector = Icons.Filled.Star,
            contentDescription = null
        )
        Text(
            text = count.toString(),
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

private class StarCountPreviewParameterProvider : PreviewParameterProvider<Int> {
    override val values = sequenceOf(
        10,
        50
    )
}


@Preview
@Composable
private fun RepositoryItemPreview(
    @PreviewParameter(StarCountPreviewParameterProvider::class) star: Int
) {
    GithubTheme {
        RepositoryItem(
            fullName = "next-step/nextstep-docs",
            description = "nextstep 매뉴얼 및 문서를 관리하는 저장소",
            stars = star,
            modifier = Modifier.fillMaxWidth()
        )
    }
}
