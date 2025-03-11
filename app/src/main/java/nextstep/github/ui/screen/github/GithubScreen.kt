package nextstep.github.ui.screen.github

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
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
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.R
import nextstep.github.data.model.RepositoryModel
import nextstep.github.ui.screen.github.component.GithubRepoListContainer
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

@Preview(showBackground = true)
@Composable
private fun GithubScreenPreview() {
    val dummyList = List(10) {
        RepositoryModel(
            id = it,
            fullName = "next-step/nextstep-docs",
            description = "nextstep 매뉴얼 및 문서를 관리하는 저장소",
        )
    }
    GithubScreen(
        repositoryList = dummyList
    )
}
