package nextstep.github.ui.view.github.repository.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.github.ui.model.GithubRepositoryModel

@Composable
fun GithubRepositoryListFoundScreen(
    items: List<GithubRepositoryModel>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(items = items) {
            GithubRepositoryListItem(
                modifier = Modifier.fillMaxWidth(),
                fullName = it.fullName,
                description = it.description,
                stars = it.stars,
                isHot = it.isHot
            )
            HorizontalDivider()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GithubRepositoryListFoundScreenPreview() {
    GithubRepositoryListFoundScreen(
        items = List(10) {
            GithubRepositoryModel(
                fullName = "next-step/nextstep-docs",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소",
                stars = 0,
                isHot = false,
            )
        }
    )
}
