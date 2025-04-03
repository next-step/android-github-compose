package nextstep.github.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.github.data.repository.model.RepositoryEntity
import nextstep.github.ui.theme.GithubTheme

@Composable
fun RepositoryItem(
    repository: RepositoryEntity,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(color = MaterialTheme.colorScheme.surface)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = repository.fullName.orEmpty(),
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )
            Text(
                text = repository.description.orEmpty(),
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Black
            )
        }
        HorizontalDivider()
    }
}

@Preview
@Composable
private fun RepositoryItemPreview() {
    GithubTheme {
        RepositoryItem(
            RepositoryEntity(
                id = 0,
                fullName = "next-step/nextstep-docs",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소",
                stars = 500
            ),
            modifier = Modifier.fillMaxWidth()
        )
    }
}
