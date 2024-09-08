package nextstep.github

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import nextstep.github.data.service.GithubService
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Retrofit

internal class GithubServiceTest {
    private val serialization = Json { ignoreUnknownKeys = true }

    private lateinit var mockWebServer: MockWebServer
    private lateinit var githubService: GithubService

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        githubService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/").toString())
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(serialization.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(GithubService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getRepositories는_성공_시_올바른_데이터를_반환한다`() = runBlocking {
        //GIVEN
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(
                """
                    [
                        {"full_name": "next-step/nextstep-docs1", "description": "Description 1"},
                        {"full_name": "next-step/nextstep-docs2", "description": "Description 2"}
                    ]
                """.trimIndent()
            )

        mockWebServer.enqueue(mockResponse)

        // When
        val response = githubService.getRepositories("next-step")

        // Then
        assertEquals(2, response.size)
        assertEquals("next-step/nextstep-docs1", response.getOrNull(0)?.fullName)
    }

    @Test
    fun `getRepositories는_404_오류_시_예외를_처리한다`(): Unit = runBlocking {
        //GIVEN
        val mockResponse = MockResponse()
            .setResponseCode(404)

        mockWebServer.enqueue(mockResponse)

        // When & Then
        try {
            githubService.getRepositories("404next-step404")
        } catch (e: HttpException) {
            assertEquals(404, e.code())
        }
    }

    @Test
    fun `getRepositories는_500_오류_시_예외를_처리한다`(): Unit = runBlocking {
        //GIVEN
        val mockResponse = MockResponse()
            .setResponseCode(500)

        mockWebServer.enqueue(mockResponse)

        // When & Then:
        try {
            githubService.getRepositories("500next-step500")
        } catch (e: HttpException) {
            assertEquals(500, e.code())
        }
    }
}
