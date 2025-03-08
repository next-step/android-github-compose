package nextstep.github.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.github.ui.github.component.GithubTopBar
import org.junit.Rule
import org.junit.Test

class GithubTopBarTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `초기화면에_타이틀이_올바르게_보여야_한다`() {
        //given, when
        composeTestRule.setContent {
            GithubTopBar()
        }

        //then
        composeTestRule
            .onNodeWithText("NEXTSTEP Repositories")
            .assertIsDisplayed()

    }
}