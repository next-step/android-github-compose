package nextstep.github.data.repositories.impls

import kotlinx.coroutines.flow.Flow
import nextstep.github.data.repositories.GithubRepoRepository
import nextstep.github.model.GitHubRepo

class FakeGithubRepoRepository(
    private val fakeGitHubReposStream: Flow<List<GitHubRepo>>,
) : GithubRepoRepository {

    override fun getGitHubReposStream(organization: String): Flow<List<GitHubRepo>> {
        return fakeGitHubReposStream
    }

    companion object {
        val gitHubRepos = List(30) {
            GitHubRepo(
                id = it.toLong(),
                fullName = "next-step/nextstep-docs-$it",
                description = if (it % 2 == 0) "nextstep 매뉴얼 및 문서를 관리하는 저장소" else null,
                stars = if (it % 2 == 0) 50 else 49,
            )
        }
    }
}
