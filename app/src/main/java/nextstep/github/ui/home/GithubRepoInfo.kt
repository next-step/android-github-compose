package nextstep.github.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.github.R
import nextstep.github.domain.model.GithubRepo
import nextstep.github.domain.model.dummyDefaultGithubRepo

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
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            if (githubRepo.isHotRepo) {
                Text(
                    text = stringResource(id = R.string.repo_hot),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = stringResource(id = R.string.repo_star_icon_description)
                )
                Text(
                    text = githubRepo.stars.toString(),
                    style = MaterialTheme.typography.labelLarge,
                )
            }
        }
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

@Preview
@Composable
private fun GithubRepoInfoPreview() {
    GithubRepoInfo(githubRepo = dummyDefaultGithubRepo)
}
