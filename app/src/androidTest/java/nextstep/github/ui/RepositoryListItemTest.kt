package nextstep.github.ui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.github.ui.model.UiRepository
import nextstep.github.ui.repository.component.RepositoryListItem
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RepositoryListItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val item = mutableStateOf(
        UiRepository(
            fullName = "",
            description = null,
            stars = 0,
        )
    )

    @Before
    fun setup() {
        composeTestRule.setContent {
            RepositoryListItem(item = item.value)
        }
    }

    @Test
    fun `stars가_4자리수_넘어가면_포멧팅_되어_보여야_한다`() {
        // given
        val item = UiRepository(
            fullName = "",
            description = null,
            stars = 1234,
        )

        // when
        this.item.value = item

        // then
        composeTestRule
            .onNodeWithText("1,234")
            .assertExists()
    }

}
