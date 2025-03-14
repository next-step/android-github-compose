package nextstep.github.ui.list

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import nextstep.github.domain.model.Repository
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GitHubRepositoryListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var uiState: GitHubRepositoryListState by mutableStateOf(GitHubRepositoryListState.Loading)
    val snackBarHostState = SnackbarHostState()

    @Before
    fun setup() {
        composeTestRule.setContent {
            GitHubRepositoryListScreen(
                uiState = uiState,
                snackBarHostState = snackBarHostState,
            )
        }
    }

    @Test
    fun `리스트_스크린의_타이틀이_보인다`() {
        composeTestRule
            .onNodeWithText("NEXTSTEP Repositories")
            .assertIsDisplayed()
    }

    @Test
    fun `로딩중에는_원형_프로그래스바가_보인다`() {
        uiState = GitHubRepositoryListState.Loading

        composeTestRule
            .onNodeWithTag("CircularProgressIndicator")
            .assertIsDisplayed()
    }

    @Test
    fun `레포지토리가_없는경우_안내_문구가_보인다`() {
        uiState = GitHubRepositoryListState.Empty

        composeTestRule
            .onNodeWithText("목록이 비었습니다.")
            .assertIsDisplayed()
    }

    @Test
    fun `리스트_목록을_보여준다`() {
        val repositories = List(100) {
            Repository(
                id = it,
                fullName = "풀무원말고풀네임$it",
                description = "설탕말고설명$it"
            )
        }
        uiState = GitHubRepositoryListState.Repositories(repositories)

        composeTestRule
            .onNodeWithText("풀무원말고풀네임1")
            .assertIsDisplayed()

        composeTestRule
            .onNode(hasScrollAction())
            .performScrollToIndex(repositories.lastIndex)

        composeTestRule
            .onNodeWithText("풀무원말고풀네임99")
            .assertIsDisplayed()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `주어진_스낵바_메시지와_라벨이_보인다`() = runTest {
        launch {
            snackBarHostState.showSnackbar(
                message = "예상치 못한 오류가 발생했습니다.",
                actionLabel = "재시도"
            )
        }

        advanceUntilIdle()

        composeTestRule
            .onNodeWithText("예상치 못한 오류가 발생했습니다.")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("재시도")
            .assertIsDisplayed()

        snackBarHostState.currentSnackbarData?.dismiss()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `액션_라벨을_누르면_스낵바가_사라진다`() = runTest {
        launch {
            snackBarHostState.showSnackbar(
                message = "메세지",
                actionLabel = "액션라벨"
            )
        }

        advanceUntilIdle()

        composeTestRule
            .onNodeWithText("액션라벨")
            .performClick()

        advanceUntilIdle()

        composeTestRule
            .onNodeWithText("메세지")
            .assertDoesNotExist()
    }
}
