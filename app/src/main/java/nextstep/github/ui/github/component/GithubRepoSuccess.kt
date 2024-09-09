package nextstep.github.ui.github.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.github.model.GithubRepo
import nextstep.github.ui.theme.GithubTheme

@Composable
fun GithubRepoSuccess(
    repositories: List<GithubRepo>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(repositories) { repo ->
            GithubRepoItem(githubRepo = repo)
            HorizontalDivider()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GithubRepoSuccessPreview() {
    GithubTheme {
        GithubRepoSuccess(
            repositories = listOf(
                GithubRepo(1, "NextStep/Test", "테스트 저장소"),
                GithubRepo(2, "NextStep/Test2", "테스트 저장소2"),
                GithubRepo(3, "NextStep/Test3", "테스트 저장소3"),
                GithubRepo(4, "NextStep/Test4", "테스트 저장소4"),
                GithubRepo(5, "NextStep/Test5", "테스트 저장소5"),
                GithubRepo(6, "NextStep/Test6", "테스트 저장소6"),
            )
        )
    }
}