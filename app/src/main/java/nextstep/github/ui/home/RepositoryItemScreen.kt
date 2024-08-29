package nextstep.github.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.github.data.RepositoryEntity

@Composable
fun RepositoryItem(
    repository: RepositoryEntity,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth().height(80.dp).padding(start = 15.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = repository.fullName ?: "",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = repository.description ?: "",
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RepositoryListPreview() {
    RepositoryItem(repository = RepositoryEntity("123", "456"))
}
