package nextstep.github.ui.repo_list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.github.data.entity.RepositoryEntity

@Composable
fun RepositoryItem(
    repositoryEntity: RepositoryEntity,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = repositoryEntity.fullName.orEmpty(),
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = repositoryEntity.description.orEmpty(),
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RepositoryItemPreview() {
    val repositoryEntity = RepositoryEntity(
        fullName = "full name",
        description = "description"
    )

    RepositoryItem(repositoryEntity = repositoryEntity)

}
