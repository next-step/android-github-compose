package nextstep.github

import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import nextstep.github.data.remote.api.GithubService
import nextstep.github.data.remote.mapper.toDataList
import nextstep.github.data.remote.response.RepositoryResponse
import nextstep.github.data.repository.GithubRepositoryImpl
import org.junit.Before
import org.junit.Test

class GithubRepositoryImplTest {

    private lateinit var githubService: GithubService
    private lateinit var repositoryImpl: GithubRepositoryImpl

    @Before
    fun setUp() {
        githubService = mockk()
        repositoryImpl = GithubRepositoryImpl(githubService)
    }

    @Test
    fun `getRepositories returns success when githubService returns valid data`() = runTest {
        // given: 테스트용 데이터
        val fakeResponse = listOf(
            RepositoryResponse(1, "Test/Repo", "Description", 100)
        )

        // githubService의 getRepositories 메서드가 성공적인 응답을 반환하도록 모킹
        coEvery { githubService.getRepositories("test-org") } returns fakeResponse

        // when: repositoryImpl의 getRepositories 호출
        val result = repositoryImpl.getRepositories("test-org")

        // then: 결과가 성공인지, 반환 데이터가 올바른지 검증
        assertTrue(result.isSuccess)
        result.onSuccess { data ->
            assertEquals(fakeResponse.toDataList(), data)
        }
        coVerify { githubService.getRepositories("test-org") }
    }

    @Test
    fun `getRepositories returns failure when githubService throws exception`() = runTest {
        // given: githubService가 예외를 발생하도록 모킹
        coEvery { githubService.getRepositories("test-org") } throws Exception("Service failure")

        // when: getRepositories 호출
        val result = repositoryImpl.getRepositories("test-org")

        // then: 결과가 실패이고, 예외 메시지가 "Service failure"인지 검증
        assertTrue(result.isFailure)
        result.exceptionOrNull()?.let { exception ->
            assertEquals("Service failure", exception.message)
        }
        coVerify { githubService.getRepositories("test-org") }
    }
}
