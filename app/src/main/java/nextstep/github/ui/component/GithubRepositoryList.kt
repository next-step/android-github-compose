package nextstep.github.ui.component

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.github.ui.model.GithubRepositoryModel

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
            GithubRepository(it)
        }
    }
}

@Preview
@Composable
private fun GithubRepositoryListPreview() {

}