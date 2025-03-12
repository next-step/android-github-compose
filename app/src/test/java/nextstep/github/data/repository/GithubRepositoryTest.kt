package nextstep.github.data.repository

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.test.runTest
import nextstep.github.data.api.GithubService
import nextstep.github.data.api.model.RepositoryEntity
import nextstep.github.data.mapper.toRepository
import org.junit.Test

class GithubRepositoryTest {

    @Test
    fun `Api_결과가_올바르게_호출되어야_한다`() = runTest {

        val repositories = listOf(
            RepositoryEntity(
                id = 4,
                fullName = "next-step/nextstep-docs4",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소4",
                stars = 12
            ),
            RepositoryEntity(
                id = 5,
                fullName = "next-step/nextstep-docs5",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소5",
                stars = 1
            ),
            RepositoryEntity(
                id = 6,
                fullName = "next-step/nextstep-docs6",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소6",
                stars = 19
            )
        )

        val githubRepository: GithubRepository =
            GithubRepositoryImpl(fakeGithubService(repositories))

        assertEquals(
            repositories.map { it.toRepository() },
            githubRepository.getRepositories("next-step")
        )

    }

    companion object {
        fun fakeGithubService(repositories: List<RepositoryEntity>): GithubService =
            object : GithubService {
                override suspend fun getRepositories(organization: String): List<RepositoryEntity> =
                    repositories
            }
    }
}