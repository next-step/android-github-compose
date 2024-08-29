package nextstep.github.ui.repo

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.github.core.model.RepositoryEntity
import nextstep.github.ui.theme.GithubTheme

@Composable
fun GithubRepoRoute(modifier: Modifier = Modifier) {
    GithubRepoScreen(
        repositories = emptyList(),
        modifier = modifier,
    )
}

@Composable
internal fun GithubRepoScreen(
    repositories: List<RepositoryEntity>,
    modifier: Modifier = Modifier,
) {
}

@Preview
@Composable
private fun GithubRepoScreenPreview(
    @PreviewParameter(GithubRepoScreenProvider::class) repositories: List<RepositoryEntity>,
) {
    GithubTheme {
        GithubRepoScreen(
            repositories = repositories,
        )
    }
}

private class GithubRepoScreenProvider : PreviewParameterProvider<List<RepositoryEntity>> {
    override val values: Sequence<List<RepositoryEntity>>
        get() =
            sequenceOf(
                listOf(
                    RepositoryEntity(
                        fullName = "next-step/TDD",
                        description = "Test-Driven Development",
                    ),
                    RepositoryEntity(
                        fullName = "next-step/behavioral-objects",
                        description = "Behavioral Objects",
                    ),
                ),
            )
}
