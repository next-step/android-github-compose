package nextstep.github

import androidx.compose.ui.test.junit4.createComposeRule
import nextstep.github.util.ResourceTestRule
import org.junit.Rule

abstract class BaseComposeTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val resourceTestRule = ResourceTestRule()
}
