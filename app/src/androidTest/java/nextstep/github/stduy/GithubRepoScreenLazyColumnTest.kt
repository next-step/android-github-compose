package nextstep.github.stduy

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import nextstep.github.core.model.RepositoryEntity
import nextstep.github.ui.repo.GithubRepoScreen
import org.junit.Rule
import org.junit.Test

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
                repositories = repositories,
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
