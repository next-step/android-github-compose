package nextstep.github.domain.usecase

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.test.runTest
import nextstep.github.data.repository.GithubRepository
import nextstep.github.model.Repository
import nextstep.github.util.Result
import org.junit.Test

class GetGithubRepositoriesUseCaseTest {

    @Test
    fun `시작시_Loading이_올바르게_방출된다`() = runTest {

        val repositories = listOf(
            Repository(
                id = 10,
                fullName = "next-step/nextstep-docs10",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소10",
                stars = 1
            )
        )

        val githubRepository = object : GithubRepository {
            override suspend fun getRepositories(organization: String): List<Repository> =
                repositories
        }

        val useCase = GetGithubRepositoriesUseCase(githubRepository)

        assertEquals(useCase.invoke("next-step").first(), Result.Loading)
    }

    @Test
    fun `성공시_Success가_올바르게_방출된다`() = runTest {

        val repositories = listOf(
            Repository(
                id = 15,
                fullName = "next-step/nextstep-docs15",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소15",
                stars = 115
            ),
            Repository(
                id = 16,
                fullName = "next-step/nextstep-docs16",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소16",
                stars = 5
            ),
            Repository(
                id = 17,
                fullName = "next-step/nextstep-docs17",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소17",
                stars = 59
            )
        )

        val githubRepository = object : GithubRepository {
            override suspend fun getRepositories(organization: String): List<Repository> =
                repositories
        }

        val useCase = GetGithubRepositoriesUseCase(githubRepository)

        assertEquals(
            useCase.invoke("next-step").last(),
            Result.Success(repositories)
        )
    }

    @Test
    fun `실패시_Error가_올바르게_방출된다`() = runTest {

        val exception = Exception("에러 발생")

        val githubRepository = object : GithubRepository {
            override suspend fun getRepositories(organization: String): List<Repository> =
                throw exception
        }

        val useCase = GetGithubRepositoriesUseCase(githubRepository)

        assertEquals(useCase.invoke("next-step").last(), Result.Error(exception))
    }
}