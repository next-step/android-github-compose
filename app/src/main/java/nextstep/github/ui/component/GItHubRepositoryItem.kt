package nextstep.github.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.github.domain.model.Repository
import nextstep.github.ui.theme.GithubTheme

@Composable
fun GitHubRepositoryItem(
    repository: Repository,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(80.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Text(
            text = repository.fullName,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            modifier = Modifier
                .padding(top = 16.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
        )
        Text(
            text = repository.description,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.weight(1f))
        HorizontalDivider(modifier = Modifier.fillMaxWidth())
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
private fun GitHubRepositoryItemPreview() {
    GithubTheme {
        GitHubRepositoryItem(
            repository = Repository(
                id = 0,
                fullName = "RepositoryFullName",
                description = "RepositoryDescription",
            ),
            modifier = Modifier
        )
    }
}
