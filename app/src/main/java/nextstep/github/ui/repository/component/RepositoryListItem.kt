package nextstep.github.ui.repository.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.github.model.RepositoryEntity

@Composable
fun RepositoryListItem(
    modifier: Modifier = Modifier,
    item: RepositoryEntity,
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = item.fullName ?: "-",
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = item.description ?: "-",
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview
@Composable
private fun RepositoryListItemPreview() {
    RepositoryListItem(
        item = RepositoryEntity(
            fullName = "nextstep-nextstep-docs",
            description = "description",
        ),
    )
}