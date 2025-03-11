package nextstep.github.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.github.model.Repository
import nextstep.github.ui.github.component.RepositoryItem
import org.junit.Rule
import org.junit.Test

class RepositoryItemTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `레포지토리_이름이_올바르게_보여야_한다`() {

        //given
        val item = Repository(
            id = 1,
            fullName = "next-step/nextstep-docs1",
            description = "nextstep 매뉴얼 및 문서를 관리하는 저장소12",
            stars = 10
        )

        //when
        composeTestRule.setContent {
            RepositoryItem(item)
        }

        //then
        composeTestRule
            .onNodeWithText("next-step/nextstep-docs1")
            .assertIsDisplayed()

    }

    @Test
    fun `레포지토리_설명이_올바르게_보여야_한다`() {
        //given
        val item = Repository(
            id = 2,
            fullName = "next-step/nextstep-docs2",
            description = "nextstep 매뉴얼 및 문서를 관리하는 저장소34",
            stars = 15
        )

        //when
        composeTestRule.setContent {
            RepositoryItem(item)
        }

        //then
        composeTestRule
            .onNodeWithText("nextstep 매뉴얼 및 문서를 관리하는 저장소34")
            .assertIsDisplayed()


    }
}