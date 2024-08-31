package nextstep.github.ui.view.github.repository.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.github.model.GithubRepositoryDto
import nextstep.github.ui.view.github.repository.GithubRepositoryListUiState

@Composable
fun GithubRepositoryListSuccessScreen(
    uiState: GithubRepositoryListUiState.Success,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(items = uiState.repositories) {
            GithubRepositoryListItem(
                modifier = Modifier.fillMaxWidth(),
                fullName = it.fullName,
                description = it.description,
            )
            HorizontalDivider()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GithubRepositoryListSuccessScreenPreview() {
    GithubRepositoryListSuccessScreen(
        uiState = GithubRepositoryListUiState.Success(
            repositories = List(10) {
                GithubRepositoryDto(
                    fullName = "next-step/nextstep-docs",
                    description = "nextstep 매뉴얼 및 문서를 관리하는 저장소"
                )
            }
        )
    )
}
