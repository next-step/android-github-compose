package nextstep.github.stduy

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import nextstep.github.core.model.RepositoryEntity
import nextstep.github.ui.repo.GithubRepoScreen
import nextstep.github.ui.repo.GithubRepoUiState
import org.junit.Rule
import org.junit.Test

/**
 * [GithubRepoScreen] 내 LazyColumn 을 이용해 학습 테스트를 작성하였습니다.
 * [GithubRepoScreen] 의 테스트가 아닌, LazyColumn 내에 있는 컴포저블들이 제대로 동작하는지 확인하는 테스트이오니 양해바랍니다.
 */
class GithubRepoScreenLazyColumnTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 깃헙_저장소_목록을_화면에_보여준다() {
        // given
        val repositories = mutableListOf<RepositoryEntity>()
        (1..10).forEach { index ->
            repositories.add(
                RepositoryEntity("nextstep/compose", "갓뮤지님의 $index 강의"),
            )
            repositories.add(
                RepositoryEntity("nextstep/kotlin-tdd", "Jason님의 $index 강의"),
            )
        }

        composeTestRule.setContent {
            GithubRepoScreen(
                uiState = GithubRepoUiState.Success(repositories),
            )
        }

        composeTestRule
            .onNodeWithText("갓뮤지님의 1 강의")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithTag("GithubRepoCards")
            .performScrollToIndex(repositories.size - 1)

        composeTestRule
            .onNodeWithText("Jason님의 10 강의")
            .assertIsDisplayed()

        composeTestRule.waitForIdle()
    }
}
