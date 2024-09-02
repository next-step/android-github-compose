package nextstep.github.ui.repo.component

import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import nextstep.github.core.model.RepositoryEntity
import nextstep.github.ui.repo.GithubRepoUiState
import org.junit.Rule
import org.junit.Test

class GithubRepoCardsTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun HOT_카드는_HOT_태그와_별_개수를_확인할_수_있다() {
        // given
        val item = RepositoryEntity("nextstep/compose", "갓뮤지님의 1 강의", 100)
        val hotRepository = listOf(item)
        val uiState = GithubRepoUiState.Success(hotRepository)

        // when
        composeTestRule.setContent {
            GithubRepoCards(uiState)
        }

        // then
        composeTestRule
            .onNodeWithTag("HotTitle")
            .assertExists()

        composeTestRule
            .onNodeWithTag("StarCount")
            .assertTextContains(item.stars.toString())
    }

    @Test
    fun NORMAL_카드는_별_개수를_확인할_수_있다() {
        // given
        val item = RepositoryEntity("nextstep/kotlin-tdd", "Jason님의 1 강의", 49)
        val normalRepository = listOf(item)
        val uiState = GithubRepoUiState.Success(normalRepository)

        // when
        composeTestRule.setContent {
            GithubRepoCards(uiState)
        }

        // then
        composeTestRule
            .onNodeWithTag("StarCount")
            .assertTextContains(item.stars.toString())

        composeTestRule
            .onNodeWithTag("HotTitle")
            .assertDoesNotExist()
    }
}
