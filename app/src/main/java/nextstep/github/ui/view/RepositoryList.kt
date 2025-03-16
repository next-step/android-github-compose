package nextstep.github.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.github.ui.RepositoryInfo

@Composable
fun RepositoryList(
    repositories: List<RepositoryInfo>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(repositories) { repository ->
            RepositoryItem(
                fullName = repository.fullName,
                description = repository.description,
            )
        }
    }
}

@Composable
private fun RepositoryItem(
    fullName: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.background(color = MaterialTheme.colorScheme.surface)
    ) {
        Text(
            text = fullName,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
        )
        HorizontalDivider(
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.outlineVariant
        )
    }
}

@Preview
@Composable
private fun RepositoryListPreview() {
    val repositories = listOf(
        RepositoryInfo(
            fullName = "fullName 1",
            description = "description 1",
        ),

        RepositoryInfo(
            fullName = "fullName 2",
            description = "description 2",
        ),
    )
    RepositoryList(repositories = repositories)
}

