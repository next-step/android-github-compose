package nextstep.github.ui.github.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class GithubLoadingTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `초기화면에_원형_프로그래스가_보여야_한다`() {
        //given
        composeTestRule.setContent {
            GithubLoading()
        }

        //when, then
        composeTestRule
            .onNodeWithTag("Loading")
            .assertIsDisplayed()
    }
}