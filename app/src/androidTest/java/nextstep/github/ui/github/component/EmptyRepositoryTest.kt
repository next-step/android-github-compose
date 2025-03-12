package nextstep.github.ui.github.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class EmptyRepositoryTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `초기화면에_목록이_비어있는_문구가_보여야_한다`() {
        //given
        composeTestRule.setContent {
            EmptyRepository()
        }

        //when, then
        composeTestRule
            .onNodeWithText("목록이 비었습니다.")
            .assertIsDisplayed()
    }
}