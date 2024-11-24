package nextstep.github.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.github.R
import nextstep.github.model.LoadState
import nextstep.github.model.NextStepRepositoryEntity
import nextstep.github.ui.theme.Purple50
import nextstep.github.viewmodel.RepositoryListViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun RepositoryListScreen(viewModel: RepositoryListViewModel) {
    val repositories by viewModel.repositories.collectAsState()
    val loadState by viewModel.loadState.collectAsState()

    RepositoryListScreen(repositories = repositories, loadState = loadState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryListScreen(repositories: List<NextStepRepositoryEntity>, loadState: LoadState) {
    val scaffoldState = SnackbarHostState()

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
        snackbarHost = { SnackbarHost(hostState = scaffoldState) }
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
                        text = stringResource(id = R.string.empty_repositories),
                        fontSize = 24.sp
                    )
                }
            }

            is LoadState.Error -> {

            }
        }
    }
}

@Composable
fun RepositoryItem(repository: NextStepRepositoryEntity) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .background(MaterialTheme.colorScheme.surface)
    ) {
        Text(
            text = repository.fullName ?: "No name available",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
        )
        Text(
            text = repository.description ?: "No description available",
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
        )
    }
}

@Preview(showBackground = true, name = "Success 케이스")
@Composable
fun RepositoryListScreenPreview() {
    RepositoryListScreen(
        repositories = listOf(
            NextStepRepositoryEntity(
                fullName = "next-step/nextstep-study",
                description = "NextStep의 자바 백엔드 스터디 저장소"
            ),
            NextStepRepositoryEntity(
                fullName = "next-step/nextstep-docs",
                description = "NextStep의 공식 문서 저장소"
            )
        ),
        loadState = LoadState.Success
    )
}

@Preview(showBackground = true, name = "Empty 케이스")
@Composable
fun RepositoryListScreenEmptyPreview() {
    RepositoryListScreen(
        repositories = listOf(
            NextStepRepositoryEntity(
                fullName = "next-step/nextstep-study",
                description = "NextStep의 자바 백엔드 스터디 저장소"
            ),
            NextStepRepositoryEntity(
                fullName = "next-step/nextstep-docs",
                description = "NextStep의 공식 문서 저장소"
            )
        ),
        loadState = LoadState.Empty
    )
}

@Preview
@Composable
fun RepositoryItemPreview() {
    RepositoryItem(
        repository = NextStepRepositoryEntity(
            fullName = "next-step/nextstep-study",
            description = "NextStep의 자바 백엔드 스터디 저장소"
        )
    )
}
