package nextstep.github.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import nextstep.github.data.RepositoryEntity
import nextstep.github.ui.component.CircularLoading

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryList(
    uiState: RepositoryUiState,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(title = {
                Text(
                    text = "NEXTSTEP Repositories",
                    style = MaterialTheme.typography.titleLarge,
                    modifier = Modifier.fillMaxWidth().background(MaterialTheme.colorScheme.surface),
                    textAlign = TextAlign.Center

                )
            })

        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (uiState) {
                is RepositoryUiState.Success -> {
                    LazyColumn {
                        items(uiState.repositories) { repository ->
                            RepositoryItem(repository = repository)
                            HorizontalDivider()
                        }
                    }
                }

                is RepositoryUiState.Error -> {
                    Text(text = uiState.message)
                }

                is RepositoryUiState.Loading -> {
                    CircularLoading()
                }
            }
        }
    }
}

@Preview
@Composable
private fun RepositoryListLoadingPreview() {
    RepositoryList(uiState = RepositoryUiState.Loading)
}

@Preview
@Composable
private fun RepositoryListPreview() {
    RepositoryList(
        uiState = RepositoryUiState.Success(
            listOf(
                RepositoryEntity(
                    "123",
                    "456"
                ),
                RepositoryEntity(
                    "123",
                    "456"
                )
            )
        )
    )
}

@Preview
@Composable
private fun RepositoryListErrorPreview() {
    RepositoryList(uiState = RepositoryUiState.Error("Error"))
}

