package nextstep.github.ui.screen.github

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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.R
import nextstep.github.data.model.RepositoryModel
import nextstep.github.ui.theme.White

@Composable
fun GithubScreen(
    modifier: Modifier = Modifier,
    viewModel: GithubViewModel = viewModel(factory = GithubViewModel.Companion.Factory),
) {
    val repositoryList by viewModel.repositoryList.collectAsState()

    GithubScreen(
        repositoryList = repositoryList,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GithubScreen(
    repositoryList: List<RepositoryModel>,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_bar_title),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.W400
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = White
                )
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {
            GithubRepoListContainer(
                repositoryList = repositoryList,
                modifier = modifier
            )
        }
    }
}

@Composable
private fun GithubRepoListContainer(
    repositoryList: List<RepositoryModel>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier,
    ) {
        items(repositoryList) {
            GithubRepoItem(
                fullName = it.fullName,
                description = it.description,
            )
        }
    }
}

@Composable
private fun GithubRepoItem(
    fullName: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surface)
                .fillMaxWidth()
                .padding(16.dp),
        ) {
            Text(
                text = fullName,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.W400
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        HorizontalDivider(
            color = MaterialTheme.colorScheme.outlineVariant,
            thickness = 1.dp
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GithubRepoItemPreview() {
    GithubRepoItem(
        fullName = "next-step/nextstep-docs",
        description = "nextstep 매뉴얼 및 문서를 관리하는 저장소",
    )
}

@Preview(showBackground = true)
@Composable
private fun GithubRepoListContainerPreview(modifier: Modifier = Modifier) {
    val dummyList = List(3) {
        RepositoryModel(
            fullName = "next-step/nextstep-docs",
            description = "nextstep 매뉴얼 및 문서를 관리하는 저장소",
        )
    }

    GithubRepoListContainer(
        repositoryList = dummyList
    )
}

@Preview(showBackground = true)
@Composable
private fun GithubScreenPreview() {
    val dummyList = List(10) {
        RepositoryModel(
            fullName = "next-step/nextstep-docs",
            description = "nextstep 매뉴얼 및 문서를 관리하는 저장소",
        )
    }
    GithubScreen(
        repositoryList = dummyList
    )
}
