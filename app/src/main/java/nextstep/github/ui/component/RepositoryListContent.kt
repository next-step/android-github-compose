package nextstep.github.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nextstep.github.data.entity.Repository

@Composable
fun RepositoryListContent(
    repositoryList: List<Repository>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
    ) {
        items(repositoryList.size) { index ->
            RepositoryItem(
                repository = repositoryList[index],
                modifier = Modifier.fillMaxWidth()
            )
            if (index < repositoryList.lastIndex) {
                HorizontalDivider(
                    color = MaterialTheme.colorScheme.outlineVariant,
                )
            }
        }
    }
}