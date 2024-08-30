package nextstep.github.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.github.R
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
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.repository_title),
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier.background(MaterialTheme.colorScheme.surface),
                    )
                }
            )
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

class RepositoryUiStateProvider : PreviewParameterProvider<RepositoryUiState> {
    override val values = sequenceOf(
        RepositoryUiState.Loading,
        RepositoryUiState.Success(
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
        ),
        RepositoryUiState.Error("Error")
    )
}

@Preview
@Composable
private fun RepositoryListPreview(@PreviewParameter(RepositoryUiStateProvider::class) uiState: RepositoryUiState) {
    RepositoryList(
        uiState = uiState
    )
}

