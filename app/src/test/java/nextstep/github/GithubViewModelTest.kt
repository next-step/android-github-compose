package nextstep.github

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import nextstep.github.data.dto.RepositoryDto
import nextstep.github.data.repository.FakeGithubRepository
import nextstep.github.domain.GithubRepositoryUseCase
import nextstep.github.ui.screen.github.GithubViewModel
import nextstep.github.ui.uistate.UiState
import org.junit.Before
import org.junit.Test

// 단위 테스트 클래스
@OptIn(ExperimentalCoroutinesApi::class)
class GithubViewModelTest {

    private lateinit var fakeGithubRepository: FakeGithubRepository
    private lateinit var githubRepositoryUseCase: GithubRepositoryUseCase
    private lateinit var viewModel: GithubViewModel

    @Before
    fun setup() {
        fakeGithubRepository = FakeGithubRepository()
        githubRepositoryUseCase = GithubRepositoryUseCase(fakeGithubRepository)
        viewModel = GithubViewModel(githubRepositoryUseCase)
    }

    @Test
    fun `fetchRepositories success with non-empty list updates state to Success`() = runTest {
        // given: 성공 케이스, 비어있지 않은 리스트
        val repoDtos = listOf(
            RepositoryDto(
                id = 1,
                fullName = "Test/Repo",
                description = "Test description",
                stars = 100
            )
        )
        fakeGithubRepository.setFakeData(repoDtos)

        // when: fetchRepositories 호출
        viewModel.fetchRepositories("next-step")

        // then: ViewModel의 상태가 Success로 변경되어야 함
        val state = viewModel.repositoryUiModel.value
        assertTrue(state is UiState.Success)
        if (state is UiState.Success) {
            assertEquals(1, state.data.size)
            // 추가 검증: 데이터 변환이 올바르게 이루어졌는지 확인
            assertEquals("Test/Repo", state.data.first().fullName)
        }
    }

    @Test
    fun `fetchRepositories success with empty list updates state to Empty`() = runTest {
        // given: 성공 케이스, 빈 리스트 반환
        fakeGithubRepository.setFakeData(emptyList())

        // when:
        viewModel.fetchRepositories("next-step")

        // then: UiState.Empty가 되어야 함
        assertTrue(viewModel.repositoryUiModel.value is UiState.Empty)
    }

    @Test
    fun `fetchRepositories error updates state to Failure and emits error message`() = runTest {
        // given: 실패 케이스, 예외 발생
        fakeGithubRepository.setFailureMode(true)

        // when:
        viewModel.fetchRepositories("next-step")

        // then: 상태가 Failure로 변경되고, errorFlow에서 에러 메시지를 받아야 함
        val state = viewModel.repositoryUiModel.value
        assertTrue(state is UiState.Failure)
        if (state is UiState.Failure) {
            assertEquals("Fake network error", state.meesage)
        }

        // errorFlow에서 첫 번째 값을 가져와 검증
        val emittedError = viewModel.errorFlow.first()
        assertEquals("Fake network error", emittedError)
    }
}
