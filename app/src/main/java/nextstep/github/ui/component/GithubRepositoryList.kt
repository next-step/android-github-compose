package nextstep.github.ui.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.github.ui.model.GithubRepositoryModel
import nextstep.github.ui.theme.GithubTheme

@Composable
internal fun GithubRepositoryList(
    model: List<GithubRepositoryModel>, modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = model,
            key = { it.id }
        ) {
            GithubRepositoryItem(it)
            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant
            )
        }
    }
}

@Preview
@Composable
private fun GithubRepositoryListPreview() {
    GithubTheme {
        GithubRepositoryList(dummyList())
    }
}

private fun dummyList() = buildList {
    for (index in 0..30) {
        add(
            GithubRepositoryModel(
                id = index,
                fullName = "안녕 $index",
                description = "내용입니다"
            )
        )
    }
}
