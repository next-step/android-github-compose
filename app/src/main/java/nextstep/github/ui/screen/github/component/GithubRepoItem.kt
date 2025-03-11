package nextstep.github.ui.screen.github.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun GithubRepoItem(
    fullName: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(
                text = fullName,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.W400
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        HorizontalDivider(
            color = MaterialTheme.colorScheme.outlineVariant,
            thickness = 1.dp
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GithubRepoItemPreview() {
    GithubRepoItem(
        fullName = "next-step/nextstep-docs",
        description = "nextstep 매뉴얼 및 문서를 관리하는 저장소",
    )
}
