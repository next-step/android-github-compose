package nextstep.github.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.collectLatest
import nextstep.github.R
import nextstep.github.model.LoadState
import nextstep.github.model.NextStepRepositoryEntity
import nextstep.github.model.NextStepRepositoryEntity.Companion.HOT_THRESHOLD
import nextstep.github.ui.theme.Purple50
import nextstep.github.viewmodel.RepositoryListViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun RepositoryListScreen(viewModel: RepositoryListViewModel) {
    val repositories by viewModel.repositories.collectAsStateWithLifecycle()
    val loadState by viewModel.loadState.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val errorMessage = stringResource(id = R.string.repositories_error_message)
    val retryActionLabel = stringResource(id = R.string.repositories_retry_action)

    LaunchedEffect(Unit) {
        snapshotFlow { loadState }
            .collectLatest { state ->
                if (state is LoadState.Error) {
                    showSnackbar(
                        onRetry = {
                            viewModel.loadRepositories()
                        },
                        snackbarHostState,
                        errorMessage,
                        retryActionLabel
                    )
                }
            }
    }

    RepositoryListScreenContent(
        repositories = repositories,
        loadState = loadState,
        snackbarHostState = snackbarHostState
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryListScreenContent(
    repositories: List<NextStepRepositoryEntity>,
    loadState: LoadState,
    snackbarHostState: SnackbarHostState
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "NEXTSTEP Repositories",
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
            )
        },
        containerColor = MaterialTheme.colorScheme.background,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { innerPadding ->
        when (loadState) {
            is LoadState.Loading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(36.dp),
                        color = Purple50,
                        strokeWidth = 5.dp,
                    )
                }
            }

            is LoadState.Success -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize(),
                    contentPadding = innerPadding
                ) {
                    items(repositories) { repository ->
                        RepositoryItem(repository = repository)
                        HorizontalDivider()
                    }
                }
            }

            is LoadState.Empty -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.repositories_empty_message),
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
            }

            else -> {}
        }
    }
}

@Composable
fun RepositoryItem(
    repository: NextStepRepositoryEntity
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            HotText(isHot = repository.isHot(HOT_THRESHOLD))
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "★ ${repository.stars ?: "No stars available"}",
                style = MaterialTheme.typography.bodyMedium
            )
        }
        Column {
            Text(
                text = repository.fullName ?: "No name available",
                style = MaterialTheme.typography.titleLarge
            )
            Text(
                text = repository.description ?: "No description available",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Composable
fun HotText(isHot: Boolean) {
    if (isHot) {
        Text(
            text = "HOT",
            color = Purple50,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

private suspend fun showSnackbar(
    onRetry: suspend () -> Unit,
    snackbarHostState: SnackbarHostState,
    errorMessage: String,
    retryActionLabel: String
) {
    val result = snackbarHostState.showSnackbar(
        message = errorMessage,
        actionLabel = retryActionLabel,
        duration = SnackbarDuration.Long
    )
    if (result == SnackbarResult.ActionPerformed) {
        onRetry()
    }
}

@Preview(showBackground = true, name = "Success 케이스")
@Composable
private fun RepositoryListScreenPreview() {
    RepositoryListScreenContent(
        repositories = listOf(
            NextStepRepositoryEntity(
                fullName = "next-step/nextstep-study",
                description = "NextStep의 자바 백엔드 스터디 저장소",
                stars = 50
            ),
            NextStepRepositoryEntity(
                fullName = "next-step/nextstep-docs",
                description = "NextStep의 공식 문서 저장소",
                stars = 49
            )
        ),
        loadState = LoadState.Success,
        snackbarHostState = SnackbarHostState()
    )
}

@Preview(showBackground = true, name = "Empty 케이스")
@Composable
private fun RepositoryListScreenEmptyPreview() {
    RepositoryListScreenContent(
        repositories = listOf(
            NextStepRepositoryEntity(
                fullName = "next-step/nextstep-study",
                description = "NextStep의 자바 백엔드 스터디 저장소",
                stars = 50
            ),
            NextStepRepositoryEntity(
                fullName = "next-step/nextstep-docs",
                description = "NextStep의 공식 문서 저장소",
                stars = 49
            )
        ),
        loadState = LoadState.Empty,
        snackbarHostState = SnackbarHostState()
    )
}

@Preview
@Composable
private fun RepositoryItemPreview() {
    RepositoryItem(
        repository = NextStepRepositoryEntity(
            fullName = "next-step/nextstep-study",
            description = "NextStep의 자바 백엔드 스터디 저장소",
            stars = 50
        )
    )
}
