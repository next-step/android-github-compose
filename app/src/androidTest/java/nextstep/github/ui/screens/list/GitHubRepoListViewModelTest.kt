package nextstep.github.ui.screens.list

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import nextstep.github.data.repositories.impls.FakeGithubRepoRepository
import nextstep.github.util.toListDuring
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.time.Duration.Companion.milliseconds

class GitHubRepoListViewModelTest {

    private lateinit var viewmodel: GitHubRepoListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun 레포지토리_목록_UiState의_초기상태는_Loading이다() = runTest {
        // given
        viewmodel = GitHubRepoListViewModel(
            FakeGithubRepoRepository(
                fakeGitHubReposStream = flow { emit(FakeGithubRepoRepository.gitHubRepos) }
            )
        )

        // then
        assertEquals(
            GitHubRepoListUiState.Loading,
            viewmodel.uiState.value,
        )
    }

    @Test
    fun 레포지토리_목록을_불러왔을_때_빈_값이라면_UiState는_Empty다() = runTest {
        // given
        viewmodel = GitHubRepoListViewModel(
            FakeGithubRepoRepository(
                fakeGitHubReposStream = flow { emit(emptyList()) }
            )
        )

        // then
        val actualEmittedUiStates = viewmodel.uiState.toListDuring(1.milliseconds)

        assertEquals(
            GitHubRepoListUiState.Empty,
            actualEmittedUiStates.last(),
        )
    }


    @Test
    fun 레포지토리_목록을_성공적으로_가져오면_UiState는_Success다() = runTest {
        // given
        viewmodel = GitHubRepoListViewModel(
            FakeGithubRepoRepository(
                fakeGitHubReposStream = flow {
                    emit(FakeGithubRepoRepository.gitHubRepos)
                }
            )
        )

        // then
        val actualEmittedUiStates = viewmodel.uiState.toListDuring(1.milliseconds)

        assertEquals(
            GitHubRepoListUiState.Success(FakeGithubRepoRepository.gitHubRepos),
            actualEmittedUiStates.last(),
        )
    }

    @Test
    fun 레포지토리_목록을_불러오는_것에_예외가_발생하면_사이드이펙트로_전달된다() = runTest {
        // given
        viewmodel = GitHubRepoListViewModel(
            FakeGithubRepoRepository(
                fakeGitHubReposStream = flow {
                    error("예외 발생!")
                }
            )
        )

        // then
        assertTrue(
            viewmodel.sideEffect.toListDuring(1.milliseconds).last()
                    is GitHubRepoListSideEffect.ShowError,
        )
    }
}
