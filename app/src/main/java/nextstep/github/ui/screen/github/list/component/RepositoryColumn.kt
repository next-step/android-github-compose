package nextstep.github.ui.screen.github.list.component

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
import nextstep.github.core.data.GithubRepositoryInfo

@Composable
fun RepositoryColumn(repositoryInfo: GithubRepositoryInfo, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(vertical = 16.dp)
            .fillMaxWidth()
    ) {
        Text(
            text = repositoryInfo.fullName,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxWidth()
        )
        Text(
            text = repositoryInfo.description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .padding(horizontal = 16.dp,)
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxWidth()
        )
    }
    HorizontalDivider()
}

@Preview(showBackground = true)
@Composable
private fun RepositoryColumnPreview() {
    RepositoryColumn(
        repositoryInfo = GithubRepositoryInfo(
            fullName = "next-step/nextstep-study",
            description = "코드숨과 함께하는 NextStep"
        )
    )
}
