package nextstep.github.data.repository.impl

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import nextstep.github.data.repository.GithubRepository
import nextstep.github.data.response.RepositoryResponse
import nextstep.github.data.service.GithubService
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit

internal class GithubRepositoryTest {

    private val json = Json { ignoreUnknownKeys = true }
    private lateinit var mockWebServer: MockWebServer
    private lateinit var githubService: GithubService
    private lateinit var githubRepository: GithubRepository

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()

        val okHttpClient = OkHttpClient.Builder()
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
            .build()

        githubService = retrofit.create(GithubService::class.java)
        githubRepository = GithubRepositoryImpl(githubService)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun 레포지토리_목록_가져오기_성공시_리스트를_반환한다() = runBlocking {
        // Given
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(
                """
                [
                    {"description": "Repo1", "full_name": "org/Repo1"},
                    {"description": "Repo2", "full_name": "org/Repo2"}
                ]
            """.trimIndent()
            )
        mockWebServer.enqueue(mockResponse)

        // When
        val result = githubRepository.getRepositories("org")

        // Then
        val repositories = result.getOrNull()
        val expectedRepository = listOf(
            RepositoryResponse(
                fullName = "org/Repo1",
                description = "Repo1"
            ),
            RepositoryResponse(
                fullName = "org/Repo2",
                description = "Repo2"
            )
        )
        assertTrue(result.isSuccess)
        assertNotNull(repositories)
        assertEquals(2, repositories?.size)
        assertEquals(expectedRepository, repositories)
    }

    @Test
    fun 레포지토리_목록_가져오기_실패시_에러를_처리한다() = runBlocking {
        // Given
        val mockResponse = MockResponse()
            .setResponseCode(404)
        mockWebServer.enqueue(mockResponse)

        // When
        val result = githubRepository.getRepositories("invalid-org")

        // Then
        assertTrue(result.isFailure)
    }
}
