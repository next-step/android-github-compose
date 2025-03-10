package nextstep.github.ui.screens.list

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.github.model.Repository
import nextstep.github.ui.screens.list.components.RepositoryItem
import nextstep.github.ui.theme.GithubTheme

@Composable
fun RepositoryListSuccessScreen(
    state: RepositoryListUiState.Success,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(key = { item -> item.id }, items = state.repositories) { repository ->
            RepositoryItem(
                repository = repository,
                modifier = Modifier.padding(16.dp)
            )
            HorizontalDivider()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RepositoryListSuccessScreenPreview() {
    GithubTheme {
        RepositoryListSuccessScreen(
            state = RepositoryListUiState.Success(
                repositories = List(10) {
                    Repository(
                        id = it.toLong(),
                        fullName = "next-step/nextstep-docs-$it",
                        description = if (it % 2 == 0) "nextstep 매뉴얼 및 문서를 관리하는 저장소" else null,
                    )
                }
            ),
        )
    }
}
