package nextstep.github

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.test.runTest
import nextstep.github.data.dto.RepositoryDto
import nextstep.github.data.repository.FakeGithubRepository
import org.junit.Before
import org.junit.Test

class GithubRepositoryTest {
    private lateinit var githubRepository: FakeGithubRepository

    @Before
    fun setUp() {
        githubRepository = FakeGithubRepository()
    }

    @Test
    fun `getRepositories returns success with fake data when not failing`() = runTest {
        // given: FakeGithubRepository에 테스트용 데이터를 설정
        val expectedData = listOf(
            RepositoryDto(1, "Test/Repo1", "Description1", 10),
            RepositoryDto(2, "Test/Repo2", "Description2", 20)
        )
        githubRepository.setFakeData(expectedData)

        // when: getRepositories 호출
        val result = githubRepository.getRepositories("test-org")

        // then: Result가 성공이며, 반환된 데이터가 예상과 일치하는지 검증
        assertTrue(result.isSuccess)
        result.onSuccess { data ->
            assertEquals(expectedData, data)
        }
    }

    @Test
    fun `getRepositories returns failure when in failure mode`() = runTest {
        // given: 실패 모드 활성화
        githubRepository.setFailureMode(true)

        // when: getRepositories 호출
        val result = githubRepository.getRepositories("test-org")

        // then: Result가 실패 상태이며, 에러 메시지가 "Fake network error"인지 검증
        assertTrue(result.isFailure)
        result.exceptionOrNull()?.let { exception ->
            assertEquals("Fake network error", exception.message)
        }
    }
}
