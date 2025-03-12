package nextstep.github.data.repositories.impls

import kotlinx.coroutines.flow.Flow
import nextstep.github.data.repositories.GithubRepository
import nextstep.github.model.Repository

class FakeGithubRepository(
    private val fakeRepositories: Flow<List<Repository>>,
): GithubRepository {

    override fun getRepositoriesStream(organization: String): Flow<List<Repository>> {
        return fakeRepositories
    }

    companion object {
        val repositories = List(30) {
            Repository(
                id = it.toLong(),
                fullName = "next-step/nextstep-docs-$it",
                description = if (it % 2 == 0) "nextstep 매뉴얼 및 문서를 관리하는 저장소" else null,
            )
        }
    }
}
