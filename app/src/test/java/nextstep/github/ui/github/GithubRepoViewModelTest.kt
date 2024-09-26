@file:OptIn(ExperimentalCoroutinesApi::class)

package nextstep.github.ui.github

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import nextstep.github.MainDispatcherRule
import nextstep.github.model.GithubRepo
import nextstep.github.repository.FakeGithubRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class GithubRepoViewModelTest {
    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)
    private lateinit var fakeGithubRepository: FakeGithubRepository
    private lateinit var viewModel: GithubRepoViewModel

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule(testDispatcher)

    @Before
    fun setUp() {
        fakeGithubRepository = FakeGithubRepository()
        viewModel = GithubRepoViewModel(fakeGithubRepository)
    }

    @Test
    fun 레포목록_가져오기_성공_하면_레포목록이_업데이트된다() = runTest {

        advanceUntilIdle()
        val uiState = viewModel.githubUiState.value
        val expected = listOf(
            GithubRepo(
                id = 1,
                fullName = "octocat/Hello-World",
                description = "This is your first repository"
            ), GithubRepo(
                id = 2,
                fullName = "google/androidx",
                description = "Development environment for Android Jetpack extension libraries"
            ), GithubRepo(
                id = 3, fullName = "kotlin/kotlin", description = "The Kotlin Programming Language"
            ), GithubRepo(
                id = 4,
                fullName = "square/retrofit",
                description = "A type-safe HTTP client for Android and the JVM"
            ), GithubRepo(
                id = 5, fullName = "JetBrains/compose-multiplatform", description = null
            )
        )
        assertEquals(expected, uiState.repositories)

    }

    @Test
    fun 에러_발생시_isLoading은_false이고_에러가_전달되어야_한다() = testScope.runTest {
        fakeGithubRepository.shouldReturnException = true
        viewModel = GithubRepoViewModel(fakeGithubRepository)

        val uiState = viewModel.githubUiState.value
        assertFalse(uiState.isLoading)
        assertTrue(uiState.repositories.isEmpty())

        val error = viewModel.errorFlow.first()
        assertNotNull(error)
        assertEquals("network error", error.message)
    }

}
