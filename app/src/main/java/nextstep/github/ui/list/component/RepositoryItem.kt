package nextstep.github.ui.list.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.github.ui.extension.bottomBorder
import nextstep.github.ui.model.Repository
import nextstep.github.ui.theme.GithubTheme

@Composable
fun RepositoryItem(
    repository: Repository,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(color = MaterialTheme.colorScheme.surface)
            .bottomBorder(color = MaterialTheme.colorScheme.outlineVariant)
            .padding(16.dp)
    ) {
        Text(
            text = repository.fullName,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = repository.description,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Composable
fun RepositoryItemWithHorizontalDivider(
    repository: Repository,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = repository.fullName,
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = repository.description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
        HorizontalDivider(
            modifier = Modifier.align(Alignment.BottomCenter),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    }
}


@Preview(showBackground = true)
@Composable
private fun RepositoryItemPreview() {
    GithubTheme {
        RepositoryItem(
            repository = Repository(
                fullName = "Juliet McDonald",
                description = "rutrum"
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RepositoryItemWithHorizontalDividerPreview() {
    GithubTheme {
        RepositoryItemWithHorizontalDivider(
            repository = Repository(
                fullName = "Juliet McDonald",
                description = "rutrum"
            )
        )
    }
}