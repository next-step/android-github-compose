package nextstep.github.ui.repo

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import nextstep.github.core.model.RepositoryEntity
import org.junit.Rule
import org.junit.Test

class GithubRepoScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 화면_초기_진입_시_로딩_화면이_표시된다() {
        // given
        composeTestRule.setContent {
            GithubRepoScreen(
                uiState = GithubRepoUiState.Loading,
            )
        }

        // then
        composeTestRule
            .onNodeWithTag("LoadingScreen")
            .assertExists()
    }

    @Test
    fun 깃헙_목록이_빈_값일_때_목록이_비었습니다라고_표시된다() {
        // given
        composeTestRule.setContent {
            GithubRepoScreen(
                uiState = GithubRepoUiState.Empty,
            )
        }

        // then
        composeTestRule
            .onNodeWithText(EMPTY_TEXT)
            .assertExists()
    }

    @Test
    fun 깃헙_목록이_있을_때_목록이_표시된다() {
        // given
        val repositories =
            listOf(
                RepositoryEntity("nextstep/compose", "갓뮤지님의 1 강의"),
                RepositoryEntity("nextstep/kotlin-tdd", "Jason님의 1 강의"),
            )
        composeTestRule.setContent {
            GithubRepoScreen(
                uiState = GithubRepoUiState.Success(repositories),
            )
        }
        // then
        composeTestRule
            .onNodeWithTag("GithubRepoCards")
            .assertExists()
    }

    @Test
    fun 깃헙_목록_조회_실패시_에러_메시지가_표시된다() {
        // given
        composeTestRule.setContent {
            GithubRepoScreen(
                uiState = GithubRepoUiState.Error("에러가 발생했습니다."),
            )
        }
        composeTestRule.waitForIdle()

        // then
        composeTestRule
            .onNodeWithTag("Snackbar")
            .assertExists()
    }

    companion object {
        private const val EMPTY_TEXT = "목록이 비었습니다."
    }
}
