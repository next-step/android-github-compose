package nextstep.github.ui.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import kotlinx.coroutines.launch
import nextstep.github.R
import nextstep.github.domain.Repository
import nextstep.github.ui.component.CircularLoading

@SuppressLint("CoroutineCreationDuringComposition")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryList(
    uiState: RepositoryUiState,
    errorState: RepositoryErrorState,
    onRetry: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()
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
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (uiState) {
                is RepositoryUiState.Success -> {
                    if (uiState.isEmpty) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = stringResource(R.string.repository_empty),
                                style = MaterialTheme.typography.titleSmall,
                            )
                        }
                    }
                    LazyColumn {
                        items(uiState.repositories) { repository ->
                            RepositoryItem(repository = repository)
                            HorizontalDivider()
                        }
                    }
                }

                is RepositoryUiState.Loading -> {
                    CircularLoading()
                }
            }
            if (errorState is RepositoryErrorState.Error) {
                val context = LocalContext.current
                coroutineScope.launch {
                    val result = snackbarHostState.showSnackbar(
                        message = errorState.message,
                        actionLabel = context.getString(R.string.retry)
                    )
                    if (result == SnackbarResult.ActionPerformed) {
                        onRetry()
                    }
                }
            }
        }
    }
}

class RepositoryUiStateProvider : PreviewParameterProvider<RepositoryUiState> {
    override val values = sequenceOf(
        RepositoryUiState.Loading,
        RepositoryUiState.Success(
            listOf()
        ),
        RepositoryUiState.Success(
            listOf(
                Repository(
                    "123",
                    "456"
                ),
                Repository(
                    "123",
                    "456"
                )
            )
        ),
    )
}

@Preview
@Composable
private fun RepositoryListPreview(@PreviewParameter(RepositoryUiStateProvider::class) uiState: RepositoryUiState) {
    RepositoryList(
        uiState = uiState,
        errorState = RepositoryErrorState.None,
        onRetry = { }
    )
}

@Preview
@Composable
private fun RepositoryListErrorPreview(@PreviewParameter(RepositoryUiStateProvider::class) uiState: RepositoryUiState) {
    RepositoryList(
        uiState = uiState,
        errorState = RepositoryErrorState.Error("예상치 못한에러"),
        onRetry = { }
    )
}
