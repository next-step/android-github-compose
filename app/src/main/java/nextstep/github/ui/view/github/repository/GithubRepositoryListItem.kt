package nextstep.github.ui.view.github.repository

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun GithubRepositoryListItem(
    fullName: String,
    description: String?,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text(
            text = fullName,
            style = MaterialTheme.typography.titleLarge
            )
        if (!description.isNullOrBlank()) {
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GithubRepositoryListItemPreview() {
    GithubRepositoryListItem(
        fullName = "next-step/nextstep-docs",
        description = "nextstep 매뉴얼 및 문서를 관리하는 저장소"
    )
}
