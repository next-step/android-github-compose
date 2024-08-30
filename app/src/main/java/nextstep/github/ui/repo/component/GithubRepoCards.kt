package nextstep.github.ui.repo.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.github.core.model.NextStepRepository
import nextstep.github.ui.repo.GithubRepoUiState
import nextstep.github.ui.theme.GithubTheme

@Composable
internal fun GithubRepoCards(
    uiState: GithubRepoUiState.Success,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(uiState.repositories) { item ->
            GithubRepoCard(
                modifier = Modifier.fillMaxWidth(),
                decoration = {
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.outlineVariant,
                        modifier =
                            Modifier
                                .fillMaxWidth()
                                .align(alignment = Alignment.BottomCenter),
                    )
                },
                tag = {
                    if (item.stars >= 50) {
                        Text(
                            text = "🔥",
                            style = MaterialTheme.typography.titleLarge,
                        )
                    }
                },
                badge = {
                    Text(
                        text = "🌟${item.stars}",
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
            ) {
                GithubRepoCardContent(
                    fullName = item.fullName,
                    description = item.description,
                )
            }
        }
    }
}

@Composable
private fun GithubRepoCard(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    decoration: @Composable (BoxScope.() -> Unit) = {},
    tag: @Composable (() -> Unit) = {},
    badge: @Composable (() -> Unit) = {},
    content: @Composable () -> Unit,
) {
    Box(modifier = modifier) {
        Column(
            modifier = Modifier.padding(contentPadding),
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
            ) {
                tag()
                Spacer(modifier = Modifier.weight(1f))
                badge()
            }
            content()
        }
        decoration()
    }
}

@Composable
private fun GithubRepoCardContent(
    fullName: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Text(
            text = fullName,
            style = MaterialTheme.typography.titleLarge,
        )
        Text(
            text = description,
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GithubRepoCardsPreview() {
    GithubTheme {
        GithubRepoCards(
            uiState =
                GithubRepoUiState.Success(
                    listOf(
                        NextStepRepository.Hot("nextstep/compose", "갓뮤지님의 강의", 100),
                        NextStepRepository.Normal("nextstep/kotlin-tdd", "Jason님의 강의", 49),
                    ),
                ),
            modifier = Modifier.fillMaxSize(),
        )
    }
}
