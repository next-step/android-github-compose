package nextstep.github.ui.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.github.domain.model.Repository
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GitHubRepositoryItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var repository by mutableStateOf(
        Repository(
            id = 0,
            fullName = "레포지토리명",
            description = "넥스트스탭 화이팅",
            stars = 5000
        )
    )

    @Before
    fun setup() {
        composeTestRule.setContent {
            GitHubRepositoryItem(
                repository = repository
            )
        }
    }

    @Test
    fun `주어진_이름이_그대로_보인다`() {
        composeTestRule
            .onNodeWithText("레포지토리명")
            .assertIsDisplayed()
    }

    @Test
    fun `주어진_설명이_그대로_보인다`() {
        composeTestRule
            .onNodeWithText("넥스트스탭 화이팅")
            .assertIsDisplayed()
    }

    @Test
    fun `주어진_스타수가_그대로_보인다`() {
        composeTestRule
            .onNodeWithText("5000")
            .assertIsDisplayed()
    }

    @Test
    fun `스타수가_50이상이면_HOT_문구가_보인다`() {
        composeTestRule
            .onNodeWithText("HOT")
            .assertIsDisplayed()
    }

    @Test
    fun `스타수가_50미만이면_HOT_문구가_보이지_않는다`() {
        repository = repository.copy(stars = 10)

        composeTestRule
            .onNodeWithText("HOT")
            .assertIsNotDisplayed()
    }
}
