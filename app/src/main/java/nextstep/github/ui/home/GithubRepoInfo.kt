package nextstep.github.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.github.domain.model.GithubRepo
import nextstep.github.domain.model.dummyGithubRepoHot
import nextstep.github.domain.model.dummyGithubRepoNonHot

@Composable
fun GithubRepoInfo(
    githubRepo: GithubRepo,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        RepoHeaderInfo(githubRepo = githubRepo)
        Text(
            text = githubRepo.fullName,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            text = githubRepo.description,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

class GithubRepoInfoProvider : PreviewParameterProvider<GithubRepo> {
    override val values: Sequence<GithubRepo> = sequenceOf(
        dummyGithubRepoHot,
        dummyGithubRepoNonHot
    )
}

@Preview
@Composable
private fun GithubRepoInfoPreview(
    @PreviewParameter(GithubRepoInfoProvider::class) githubRepo: GithubRepo
) {
    GithubRepoInfo(githubRepo = githubRepo)
}
