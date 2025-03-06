package nextstep.github.ui.github

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.data.api.model.RepositoryEntity
import nextstep.github.ui.github.component.GithubTopBar
import nextstep.github.ui.github.component.RepositoryList

@Composable
fun GithubScreen(
    modifier: Modifier = Modifier,
    viewModel: GithubViewModel = viewModel(factory = GithubViewModel.Factory)
) {
    val repositories by viewModel.repositories.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.getRepositories()
    }

    GithubScreen(
        repositories = repositories,
        modifier = modifier
    )
}

@Composable
fun GithubScreen(
    repositories: List<RepositoryEntity>,
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = { GithubTopBar() }
    ) { contentPadding ->
        RepositoryList(
            repositories,
            modifier = Modifier.padding(contentPadding)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun GithubScreenPreview() {
    val repositories = (1..5).map {
        RepositoryEntity(
            fullName = "next-step/nextstep-docs${it}",
            description = "nextstep 매뉴얼 및 문서를 관리하는 저장소${it}"
        )
    }
    GithubScreen(repositories)

}