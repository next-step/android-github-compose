package nextstep.github.ui.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import nextstep.github.domain.model.Repository
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GitHubRepositoryListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var repositories by mutableStateOf(emptyList<Repository>())

    @Before
    fun setup() {
        composeTestRule.setContent {
            GitHubRepositoryListScreen(
                repositories = repositories,
            )
        }
    }

    @Test
    fun `리스트_스크린의_타이틀이_보인다`() {
        repositories = emptyList()

        composeTestRule
            .onNodeWithText("NEXTSTEP Repositories")
            .assertIsDisplayed()
    }

    @Test
    fun `리스트_목록을_보여준다`() {
        repositories = List(100) {
            Repository(
                id = it,
                fullName = "풀무원말고풀네임$it",
                description = "설탕말고설명$it"
            )
        }

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
}
