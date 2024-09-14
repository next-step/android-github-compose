package nextstep.github.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.github.model.NextStepRepositoryEntity
import nextstep.github.viewmodel.RepositoryListViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun RepositoryListScreenStateful(viewModel: RepositoryListViewModel) {
    var repositories by remember { mutableStateOf<List<NextStepRepositoryEntity>>(emptyList()) }

    LaunchedEffect(Unit) {
        repositories = viewModel.getRepositories("next-step")
    }

    RepositoryListScreenStateless(repositories = repositories)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RepositoryListScreenStateless(repositories: List<NextStepRepositoryEntity>) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "NEXTSTEP Repositories",
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .background(MaterialTheme.colorScheme.surface)
                    )
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            items(repositories) { repository ->
                RepositoryItem(repository = repository)
                HorizontalDivider(
                    modifier = Modifier.fillMaxWidth(),
                    thickness = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.2f)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun StatelessRepositoryListScreenPreview() {
    RepositoryListScreenStateless(
        repositories = listOf(
            NextStepRepositoryEntity(
                fullName = "next-step/nextstep-study",
                description = "NextStep의 자바 백엔드 스터디 저장소"
            ),
            NextStepRepositoryEntity(
                fullName = "next-step/nextstep-docs",
                description = "NextStep의 공식 문서 저장소"
            )
        )
    )
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
