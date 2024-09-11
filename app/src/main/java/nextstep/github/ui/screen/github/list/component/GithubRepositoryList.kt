package nextstep.github.ui.screen.github.list.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import nextstep.github.domain.entity.RepositoryEntity

@Composable
fun GithubRepositoryList(
    repositoryEntityList: List<RepositoryEntity>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth()
    ) {
        items(repositoryEntityList.size) {
            RepositoryColumn(
                repositoryInfo = repositoryEntityList[it],
                modifier = Modifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun GithubRepositoryListPreview() {
    val githubRepositoryList = listOf(
        RepositoryEntity(
            fullName = "next-step/nextstep-study",
            description = "코드숨과 함께하는 NextStep",
            stars = 3
        ),
        RepositoryEntity(
            fullName = "next-step/nextstep-study",
            description = "코드숨과 함께하는 NextStep",
            stars = 2
        ),
        RepositoryEntity(
            fullName = "next-step/nextstep-study",
            description = "코드숨과 함께하는 NextStep",
            stars = 20
        ),
        RepositoryEntity(
            fullName = "next-step/nextstep-study",
            description = "코드숨과 함께하는 NextStep",
            stars = 2
        )
    )

    GithubRepositoryList(
        repositoryEntityList = githubRepositoryList,
        modifier = Modifier
    )
}
