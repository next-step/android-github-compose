package nextstep.github.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import nextstep.github.R
import nextstep.github.domain.model.GithubRepo

@Composable
fun RepoHeaderInfo(
    githubRepo: GithubRepo,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
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
}
