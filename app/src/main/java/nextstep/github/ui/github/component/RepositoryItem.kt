package nextstep.github.ui.github.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.github.data.api.model.RepositoryEntity
import nextstep.github.ui.theme.GithubTheme

@Composable
fun RepositoryItem(
    item: RepositoryEntity,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
            text = item.fullName.orEmpty(),
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black
        )
        Text(
            text = item.description.orEmpty(),
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Black
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RepositoryItemPreview() {

    val item = RepositoryEntity(
        fullName = "next-step/nextstep-docs",
        description = "nextstep 매뉴얼 및 문서를 관리하는 저장소"
    )

    GithubTheme {
        RepositoryItem(item)
    }
}