package nextstep.github.ui.repo

import androidx.lifecycle.SavedStateHandle
import app.cash.turbine.test
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import nextstep.github.BaseTest
import nextstep.github.core.data.GithubRepository
import nextstep.github.core.domain.GeOrganizationRepositoryUseCase
import nextstep.github.core.model.Organization
import nextstep.github.core.model.OrganizationRepository
import nextstep.github.core.model.RepositoryEntity
import org.junit.Before
import org.junit.Test

class GithubRepoViewModelTest : BaseTest() {
    private lateinit var savedStateHandle: SavedStateHandle

    @OptIn(ExperimentalCoroutinesApi::class)
    @Before
    fun setUp() {
        Dispatchers.setMain(UnconfinedTestDispatcher())
        mockLogClass()
        savedStateHandle =
            SavedStateHandle().apply {
                set(KEY_ORGANIZATION, Organization.NEXT_STEP.value)
            }
    }

    @Test
    fun 데이터가_빈_값일일_때_EMPTY_상태를_반환한다() =
        runTest {
            // given
            val fakeRepository =
                object : GithubRepository {
                    override suspend fun getRepositories(organization: Organization): Result<List<RepositoryEntity>> =
                        Result.success(emptyList())
                }
            val useCase = GeOrganizationRepositoryUseCase(fakeRepository)

            val viewModel =
                GithubRepoViewModel(
                    savedStateHandle = savedStateHandle,
                    geOrganizationRepositoryUseCase = useCase,
                )
            advanceUntilIdle()
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
                    RepositoryEntity("nextstep/compose", "갓뮤지님의 1 강의", 100),
                    RepositoryEntity("nextstep/kotlin-tdd", "Jason님의 1 강의", 49),
                )
            val nextStepRepositories =
                listOf(
                    OrganizationRepository.Hot("nextstep/compose", "갓뮤지님의 1 강의", 100),
                    OrganizationRepository.Normal("nextstep/kotlin-tdd", "Jason님의 1 강의", 49),
                )
            val fakeRepository =
                object : GithubRepository {
                    override suspend fun getRepositories(organization: Organization): Result<List<RepositoryEntity>> =
                        Result.success(repositories)
                }
            val useCase = GeOrganizationRepositoryUseCase(fakeRepository)
            val viewModel =
                GithubRepoViewModel(
                    savedStateHandle = savedStateHandle,
                    geOrganizationRepositoryUseCase = useCase,
                )

            // then
            viewModel.uiState.test {
                assertEquals(
                    GithubRepoUiState.Success(
                        nextStepRepositories,
                    ),
                    awaitItem(),
                )
            }
        }

    @Test
    fun 데이터_로딩_중_에러가_발생하면_ERROR_상태를_반환한다() =
        runTest {
            // given
            val errorMessage = "error"
            val fakeRepository =
                object : GithubRepository {
                    override suspend fun getRepositories(organization: Organization): Result<List<RepositoryEntity>> {
                        // 테스트를 위한 임의 delay
                        delay(1000)
                        return Result.failure(Exception(errorMessage))
                    }
                }
            val useCase = GeOrganizationRepositoryUseCase(fakeRepository)
            val viewModel =
                GithubRepoViewModel(
                    savedStateHandle = savedStateHandle,
                    geOrganizationRepositoryUseCase = useCase,
                )
            viewModel.uiState.test { assertEquals(GithubRepoUiState.Loading, awaitItem()) }

            // then
            viewModel.effect.test {
                assertEquals(
                    GithubRepoEffect.ShowErrorMessage(errorMessage),
                    awaitItem(),
                )
            }
        }

    // 실패 후 재시도 성공
    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun 데이터_로딩_실패_후_재시도_성공하여_SUCCESS_상태를_반환한다() =
        runTest {
            // given
            val repositories =
                listOf(
                    RepositoryEntity("nextstep/compose", "갓뮤지님의 1 강의", 100),
                    RepositoryEntity("nextstep/kotlin-tdd", "Jason님의 1 강의", 49),
                )
            val nextStepRepositories =
                listOf(
                    OrganizationRepository.Hot("nextstep/compose", "갓뮤지님의 1 강의", 100),
                    OrganizationRepository.Normal("nextstep/kotlin-tdd", "Jason님의 1 강의", 49),
                )
            val fakeRepository =
                object : GithubRepository {
                    private var count = 0

                    override suspend fun getRepositories(organization: Organization): Result<List<RepositoryEntity>> =
                        if (count++ == 0) {
                            delay(1000)
                            Result.failure(Exception("error"))
                        } else {
                            Result.success(repositories)
                        }
                }
            val useCase = GeOrganizationRepositoryUseCase(fakeRepository)
            val viewModel =
                GithubRepoViewModel(
                    savedStateHandle = savedStateHandle,
                    geOrganizationRepositoryUseCase = useCase,
                )
            viewModel.uiState.test { assertEquals(GithubRepoUiState.Loading, awaitItem()) }
            viewModel.effect.test {
                assertEquals(
                    GithubRepoEffect.ShowErrorMessage("error"),
                    awaitItem(),
                )
            }
            // when
            viewModel.retry()
            advanceUntilIdle()

            // then
            viewModel.uiState.test {
                assertEquals(
                    GithubRepoUiState.Success(nextStepRepositories),
                    awaitItem(),
                )
            }
        }
}
