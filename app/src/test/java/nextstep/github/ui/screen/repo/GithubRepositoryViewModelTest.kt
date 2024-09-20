package nextstep.github.ui.screen.repo

import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import nextstep.github.data.repository.GithubRepository
import nextstep.github.data.response.RepositoryResponse
import nextstep.github.domain.model.toModel
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
internal class GithubRepositoryViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()
    private lateinit var viewModel: GithubRepositoryViewModel
    private lateinit var mockGithubRepository: GithubRepository

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        mockGithubRepository = mockk<GithubRepository>()
        viewModel = GithubRepositoryViewModel(mockGithubRepository)
    }

    @Test
    fun 레포목록_가져오기_성공_하면_로딩이끝나고_레포목록이_업데이트된다() = runTest {
        val mockRepositories = listOf(RepositoryResponse("TestRepo"))
        coEvery { mockGithubRepository.getNextStepRepositories() } returns Result.success(mockRepositories)

        viewModel.handleEvent(GithubEvent.Init)

        viewModel.state.test {
            val secondState = awaitItem()
            assertEquals(mockRepositories.map { it.toModel() }, secondState.repositories)
        }
    }

    @Test
    fun 레포목록_가져오기_실패_하면_로딩이끝나고_exception이_업데이트된다() = runTest {
        val exception = Exception("Failed")
        coEvery { mockGithubRepository.getNextStepRepositories() } returns Result.failure(exception)

        viewModel.handleEvent(GithubEvent.Init)

        viewModel.state.test {
            val secondState = awaitItem()
            assertEquals(secondState.isError, true)
        }
    }
}
