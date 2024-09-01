package nextstep.github.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
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
import nextstep.github.domain.Repository
import nextstep.github.ui.component.TextWithIcon

@Composable
fun RepositoryItem(
    repository: Repository,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                horizontal = 15.dp,
                vertical = 16.dp
            ),
        verticalArrangement = Arrangement.Center
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (repository.isHot) {
                Text(
                    text = stringResource(R.string.hot),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            TextWithIcon(
                text = repository.star.toString(),
                icon = Icons.Default.Star
            )
        }
        Text(
            text = repository.fullName,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = repository.description,
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

class RepositoryItemProvider : PreviewParameterProvider<Repository> {
    override val values = sequenceOf(
        Repository(
            "123123123123123123123123123123123123123",
            "456456456456456456456456456456456456456456456456456",
            0
        ),
        Repository(
            "123123123123123123123123123123123123123",
            "456456456456456456456456456456456456456456456456456",
            50
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun RepositoryListPreview(@PreviewParameter(RepositoryItemProvider::class) repository: Repository) {
    RepositoryItem(repository)
}
