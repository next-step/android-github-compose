package nextstep.github.ui.repository.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import nextstep.github.ui.repository.RepositoryListUiState

@Composable
fun SuccessRepositoryListContent(
    modifier: Modifier = Modifier,
    uiState: RepositoryListUiState.Success,
) {
    LazyColumn(modifier = modifier) {
        items(uiState.repositories) { item ->
            RepositoryListItem(
                item = item,
                modifier = Modifier.fillMaxWidth(),
            )
            HorizontalDivider()
        }
    }
}
