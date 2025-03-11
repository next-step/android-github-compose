package nextstep.github.ui.github.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import nextstep.github.model.Repository
import nextstep.github.ui.theme.GithubTheme


@Composable
fun RepositoryList(
    items: List<Repository>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.surface)
    ) {
        items(items, key = { item -> item.id }) { item ->
            RepositoryItem(item)
            HorizontalDivider(
                color = MaterialTheme.colorScheme.outlineVariant,
                modifier = Modifier.testTag("HorizontalDivider")
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RepositoryListPreview() {
    val list = (1..5).map {
        Repository(
            id = it,
            fullName = "next-step/nextstep-docs${it}",
            description = "nextstep 매뉴얼 및 문서를 관리하는 저장소${it}",
            stars = it * 2
        )
    }
    GithubTheme {
        RepositoryList(list)
    }
}