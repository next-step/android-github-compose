package nextstep.github

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import nextstep.github.data.api.model.RepositoryEntity
import nextstep.github.data.repository.GithubRepository
import nextstep.github.ui.github.GithubViewModel
import org.junit.Test

class GithubViewModelTest {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `레포지토리_결과값이_올바르게_전달되어야_한다`() = runTest {
        //given
        val repositories = listOf(
            RepositoryEntity(
                id = 1,
                fullName = "next-step/nextstep-docs1",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소1"
            ),
            RepositoryEntity(
                id = 2,
                fullName = "next-step/nextstep-docs2",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소2"
            )
        )
        val githubViewModel = GithubViewModel(fakeGithubRepository(repositories))

        advanceUntilIdle()

        //when, then
        assertEquals(repositories, githubViewModel.repositories.value)
    }

    companion object {
        fun fakeGithubRepository(repositories: List<RepositoryEntity>): GithubRepository =
            object : GithubRepository {
                override suspend fun getRepositories(organization: String): List<RepositoryEntity> =
                    repositories
            }
    }
}