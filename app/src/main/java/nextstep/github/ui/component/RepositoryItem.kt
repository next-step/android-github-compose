package nextstep.github.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.github.ui.model.RepositoryUiModel
import nextstep.github.ui.theme.GithubTheme

@Composable
fun RepositoryItem(
    repository: RepositoryUiModel,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.surface)
            .padding(16.dp)
    ) {
        Text(
            text = repository.fullName,
            style = MaterialTheme.typography.titleLarge,
            modifier = modifier,
        )
        Text(
            text = repository.description,
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier,
        )
    }
}

@Preview
@Composable
private fun RepositoryHotItemPreview() {
    GithubTheme {
        RepositoryItem(
            repository = RepositoryUiModel(
                fullName = "nextstep/github",
                description = "Github Repository for NextStep",
                stars = 50,
                isHot = true,
            )
        )
    }
}

@Preview
@Composable
private fun RepositoryItemPreview() {
    GithubTheme {
        RepositoryItem(
            repository = RepositoryUiModel(
                fullName = "nextstep/github",
                description = "Github Repository for NextStep",
                stars = 0,
                isHot = false,
            )
        )
    }
}