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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.github.ui.model.RepositoryModel
import nextstep.github.ui.theme.GithubTheme

@Composable
internal fun RepositoryItem(
    repositoryModel: RepositoryModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .padding(16.dp)
        ) {
            Text(
                text = repositoryModel.fullName,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = repositoryModel.description,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        HorizontalDivider(
            color = MaterialTheme.colorScheme.outlineVariant
        )
    }

}

@Preview(showBackground = true, name = "RepositoryItem")
@Composable
private fun Preview1() {
    GithubTheme {
        RepositoryItem(
            RepositoryModel(
                fullName = "next-step/nextstep-docs",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소"
            )
        )
    }
}
