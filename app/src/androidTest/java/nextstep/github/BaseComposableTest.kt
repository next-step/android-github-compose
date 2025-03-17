package nextstep.github

import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule

abstract class BaseComposableTest {
    @get:Rule
    val composeTestRule = createComposeRule()
}
