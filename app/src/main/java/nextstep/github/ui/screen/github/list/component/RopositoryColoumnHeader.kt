package nextstep.github.ui.screen.github.list.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import nextstep.github.core.data.GithubRepositoryInfo

@Composable
fun RepositoryColumnHeader(
    repositoryInfo: GithubRepositoryInfo,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        if(repositoryInfo.stars > 50) {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically),
                text = stringResource(id = R.string.text_hot),
                style = MaterialTheme.typography.labelLarge,
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = stringResource(id = R.string.text_description_repository_star_icon)
        )

        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = "${repositoryInfo.stars}",
            style = MaterialTheme.typography.labelLarge,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RepositoryColumnHeaderPreview() {
    RepositoryColumnHeader(
        repositoryInfo = GithubRepositoryInfo(
            fullName = "next-step/nextstep-study",
            description = "코드숨과 함께하는 NextStep",
            stars = 32
        )
    )
}
