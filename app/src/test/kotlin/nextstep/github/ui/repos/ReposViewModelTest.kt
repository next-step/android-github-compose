package nextstep.github.ui.repos

import app.cash.turbine.test
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import nextstep.github.ui.model.UiGitHubRepoInfo
import nextstep.github.ui.usecase.GetGitHubRepositoryUseCase
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ReposViewModelTest {
    private lateinit var scheduler: TestCoroutineScheduler

    @Before
    fun setUp() {
        // given: 테스트에 사용할 스케줄러와 디스패처 설정
        scheduler = TestCoroutineScheduler()
        val testDispatcher = StandardTestDispatcher(scheduler)
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        // after: 메인 디스패처를 원래 상태로 복원
        Dispatchers.resetMain()
    }

    @Test
    fun `레포를 가져올 때 에러가 발생하면 uiState는 ReposUiState Error이다`() = runTest {
        // given: 레포지토리를 가져오는 유즈케이스가 예외를 발생시킴
        val getGitHubRepositoryUseCase = object : GetGitHubRepositoryUseCase {
            override suspend fun invoke(organization: String): List<UiGitHubRepoInfo> {
                throw Exception()
            }
        }

        // when: 에러가 발생하는 상황에서 ViewModel이 생성됨
        val reposViewModel = ReposViewModel(getGitHubRepositoryUseCase)

        // then: 상태 변화가 Loading -> Error로 진행되는지 확인
        reposViewModel.repos.test {
            val initial = awaitItem()
            val latest = awaitItem()
            assert(initial is ReposUiState.Loading)
            assert(latest is ReposUiState.Error)
        }
    }

    @Test
    fun `레포를 가져올 때 리스트가 제대로 들어있다면 uiState는 ReposUiState Success이다`() = runTest {
        // given: 레포지토리를 성공적으로 가져오는 유즈케이스가 빈 리스트를 반환함
        val getGitHubRepositoryUseCase = object : GetGitHubRepositoryUseCase {
            override suspend fun invoke(organization: String): List<UiGitHubRepoInfo> {
                return emptyList()
            }
        }

        // when: 성공적인 상황에서 ViewModel이 생성됨
        val reposViewModel = ReposViewModel(getGitHubRepositoryUseCase)

        // then: 상태 변화가 Loading -> Success로 진행되는지 확인
        reposViewModel.repos.test {
            val initial = awaitItem()
            val latest = awaitItem()
            assert(initial is ReposUiState.Loading)
            assert(latest is ReposUiState.Success)
        }
    }
}