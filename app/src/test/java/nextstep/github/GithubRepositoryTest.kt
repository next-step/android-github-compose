import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.MockK
import io.mockk.junit4.MockKRule
import kotlinx.coroutines.runBlocking
import nextstep.github.data.model.response.RepositoryResponse
import nextstep.github.data.repository.GithubRepository
import nextstep.github.data.repository.GithubRepositoryImpl
import nextstep.github.data.service.GithubService
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response

internal class GithubRepositoryTest {

    @get:Rule
    val mockkRule = MockKRule(this)

    @MockK
    private lateinit var githubService: GithubService

    private lateinit var githubRepository: GithubRepository

    @Before
    fun setUp() {
        githubRepository = GithubRepositoryImpl(githubService)
    }

    @Test
    fun `getRepositories는_성공_시_올바른_데이터를_반환한다`() = runBlocking {
        // Given
        val organization = "next-step"
        val expectedRepositories = listOf(
            RepositoryResponse(fullName = "next-step/nextstep-docs1", description = "Description 1"),
            RepositoryResponse(fullName = "next-step/nextstep-docs2", description = "Description 2")
        )
        coEvery { githubService.getRepositories(organization) } returns expectedRepositories

        // When
        val repositories = githubRepository.getRepositories(organization)

        // Then
        assertEquals(expectedRepositories, repositories)

        coVerify { githubService.getRepositories(organization) }
    }

    @Test
    fun `getRepositories는_404_오류_시_예외를_처리한다`() = runBlocking {
        // Given
        val organization = "nonexistent-org"
        coEvery { githubService.getRepositories(organization) } throws HttpException(
            Response.error<Any>(404, "Not Found".toResponseBody(null))
        )

        // When & Then
        try {
            githubRepository.getRepositories(organization)
        } catch (e: Exception) {
            assertTrue(e is HttpException)
            assertEquals(404, (e as HttpException).code())
        }

        coVerify { githubService.getRepositories(organization) }
    }

    @Test
    fun `getRepositories는_500_오류_시_예외를_처리한다`() = runBlocking {
        // Given
        val organization = "next-step"
        coEvery { githubService.getRepositories(organization) } throws HttpException(
            Response.error<Any>(500, "Internal Server Error".toResponseBody(null))
        )

        // When & Then
        try {
            githubRepository.getRepositories(organization)
        } catch (e: Exception) {
            assertTrue(e is HttpException)
            assertEquals(500, (e as HttpException).code())
        }

        coVerify { githubService.getRepositories(organization) }
    }
}
