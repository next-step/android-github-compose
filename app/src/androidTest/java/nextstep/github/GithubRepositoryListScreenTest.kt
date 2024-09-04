package nextstep.github

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import junit.framework.TestCase.assertEquals
import nextstep.github.ui.view.github.repository.screen.GithubRepositoryListScreen
import org.junit.Rule
import org.junit.Test

class GithubRepositoryListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 에러_발생시_스낵바_노출() {
        // Given
        val isError = mutableStateOf(false)
        composeTestRule.setContent {
            GithubRepositoryListScreen(
                items = emptyList(),
                isLoading = false,
                isError = isError.value,
                onRetry = {},
            )
        }

        // when
        composeTestRule.onNodeWithText("예상치 못한 오류가 발생했습니다.")
            .assertDoesNotExist()
        isError.value = true

        // Then
        composeTestRule.onNodeWithText("예상치 못한 오류가 발생했습니다.")
            .assertExists()
    }

    @Test
    fun 에러_발생시_스낵바_재시도_클릭하면_재시도_실행() {
        // Given
        val isError = mutableStateOf(true)
        composeTestRule.setContent {
            GithubRepositoryListScreen(
                items = emptyList(),
                isLoading = false,
                isError = isError.value,
                onRetry = { isError.value = false },
            )
        }

        // When
        composeTestRule.onNodeWithText("예상치 못한 오류가 발생했습니다.")
            .assertExists()
        composeTestRule.onNodeWithText("재시도")
            .performClick()

        // Then
        assertEquals(isError.value, false)
    }
}
