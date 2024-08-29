package nextstep.github.ui.repo

import app.cash.turbine.test
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import nextstep.github.BaseTest
import nextstep.github.core.data.GithubRepository
import nextstep.github.core.model.RepositoryEntity
import org.junit.Before
import org.junit.Test

class GithubRepoViewModelTest : BaseTest() {
    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        mockLogClass()
    }

    @Test
    fun 데이터가_빈_값일일_때_EMPTY_상태를_반환한다() =
        runTest {
            // given
            val fakeRepository =
                object : GithubRepository {
                    override suspend fun getRepositories(organization: String): Result<List<RepositoryEntity>> = Result.success(emptyList())
                }
            val viewModel = GithubRepoViewModel(fakeRepository)

            // then
            viewModel.uiState.test {
                assertEquals(GithubRepoUiState.Empty, awaitItem())
            }
        }

    @Test
    fun 데이터가_있을_때_SUCCESS_상태를_반환한다() =
        runTest {
            // given
            val repositories =
                listOf(
                    RepositoryEntity("nextstep/compose", "갓뮤지님의 1 강의"),
                    RepositoryEntity("nextstep/kotlin-tdd", "Jason님의 1 강의"),
                )
            val fakeRepository =
                object : GithubRepository {
                    override suspend fun getRepositories(organization: String): Result<List<RepositoryEntity>> =
                        Result.success(repositories)
                }
            val viewModel = GithubRepoViewModel(fakeRepository)

            // then
            viewModel.uiState.test {
                assertEquals(
                    GithubRepoUiState.Success(
                        repositories,
                    ),
                    awaitItem(),
                )
            }
        }

    @Test
    fun 데이터_로딩_중_에러가_발생하면_ERROR_상태를_반환한다() =
        runTest {
            // given
            mockLogClass()
            val errorMessage = "error"
            val fakeRepository =
                object : GithubRepository {
                    override suspend fun getRepositories(organization: String): Result<List<RepositoryEntity>> =
                        Result.failure(Exception(errorMessage))
                }
            val viewModel = GithubRepoViewModel(fakeRepository)

            // then
            viewModel.uiState.test {
                assertEquals(
                    GithubRepoUiState.Error(errorMessage),
                    awaitItem(),
                )
            }
        }
}
