package nextstep.github.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.CollectionPreviewParameterProvider
import androidx.compose.ui.unit.dp
import nextstep.github.R
import nextstep.github.domain.model.Repository
import nextstep.github.ui.preview.BackgroundPreview
import nextstep.github.ui.theme.GithubTheme

@Composable
fun GitHubRepositoryItem(
    repository: Repository,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(100.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        val itemsModifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
        StarContents(
            repository = repository,
            modifier = itemsModifier.padding(top = 16.dp),
        )
        Text(
            text = repository.fullName,
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            modifier = itemsModifier,
        )
        Text(
            text = repository.description,
            style = MaterialTheme.typography.bodyMedium,
            maxLines = 1,
            modifier = itemsModifier,
        )
        Spacer(modifier = Modifier.weight(1f))
        HorizontalDivider(modifier = Modifier.fillMaxWidth())
    }
}

@Composable
private fun StarContents(
    repository: Repository,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (repository.isHot) {
            Text(
                text = stringResource(R.string.hot),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary,
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Image(
            imageVector = Icons.Filled.Star,
            contentDescription = null,
            modifier = Modifier.size(18.dp)
        )
        Text(
            text = repository.stars.toString(),
            style = MaterialTheme.typography.labelLarge,
            color = Color.Black
        )
    }
}

@BackgroundPreview
@Composable
private fun GitHubRepositoryItemPreview(
    @PreviewParameter(GitHubRepositoryItemPreviewParameter::class) repository: Repository
) {
    GithubTheme {
        GitHubRepositoryItem(
            repository = repository,
            modifier = Modifier
        )
    }
}

private class GitHubRepositoryItemPreviewParameter : CollectionPreviewParameterProvider<Repository>(
    listOf(
        Repository(
            id = 0,
            fullName = "RepositoryFullName",
            description = "RepositoryDescription",
            stars = 0
        ),
        Repository(
            id = 0,
            fullName = "RepositoryFullName",
            description = "RepositoryDescription",
            stars = 50
        )
    )
)
