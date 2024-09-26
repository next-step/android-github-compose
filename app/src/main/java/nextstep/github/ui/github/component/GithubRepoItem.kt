package nextstep.github.ui.github.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.github.model.GithubRepo
import nextstep.github.ui.theme.GithubTheme

@Composable
fun GithubRepoItem(
    githubRepo: GithubRepo,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
            text = githubRepo.fullName,
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = githubRepo.description ?: "",
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GithubRepositoriesItemPreview() {
    GithubTheme {
        GithubRepoItem(
            GithubRepo(
                id = 1,
                fullName = "NextStep/Test",
                description = "테스트 저장소"
            )
        )
    }
}