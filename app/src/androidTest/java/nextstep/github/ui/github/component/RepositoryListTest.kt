package nextstep.github.ui.github.component

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithText
import nextstep.github.model.Repository
import org.junit.Rule
import org.junit.Test

class RepositoryListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `여러개의_레포지토리의_이름이_올바르게_보여야_한다`() {
        //given
        val items = listOf(
            Repository(
                id = 1,
                fullName = "next-step/nextstep-docs1",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소1",
                stars = 2
            ),
            Repository(
                id = 2,
                fullName = "next-step/nextstep-docs2",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소2",
                stars = 3
            )
        )
        //when
        composeTestRule.setContent {
            RepositoryList(items)
        }

        //then
        composeTestRule
            .onNodeWithText("next-step/nextstep-docs1")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("next-step/nextstep-docs2")
            .assertIsDisplayed()
    }

    @Test
    fun `여러개의_레포지토리의_설명이_올바르게_보여야_한다`() {

        //given
        val items = listOf(
            Repository(
                id = 3,
                fullName = "next-step/nextstep-docs3",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소3",
                stars = 10
            ),
            Repository(
                id = 4,
                fullName = "next-step/nextstep-docs4",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소4",
                stars = 6
            )
        )
        //when
        composeTestRule.setContent {
            RepositoryList(items)
        }

        //then
        composeTestRule
            .onNodeWithText("nextstep 매뉴얼 및 문서를 관리하는 저장소3")
            .assertIsDisplayed()

        composeTestRule
            .onNodeWithText("nextstep 매뉴얼 및 문서를 관리하는 저장소4")
            .assertIsDisplayed()
    }

    @Test
    fun `아이템의_갯수만큼_분리선이_보여야_한다`() {

        //given
        val items = listOf(
            Repository(
                id = 1,
                fullName = "next-step/nextstep-docs1",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소1",
                stars = 3
            ),
            Repository(
                id = 2,
                fullName = "next-step/nextstep-docs2",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소2",
                stars = 5
            ),
            Repository(
                id = 3,
                fullName = "next-step/nextstep-docs3",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소3",
                stars = 7
            )
        )
        //when
        composeTestRule.setContent {
            RepositoryList(items)
        }

        //then
        composeTestRule
            .onAllNodesWithTag("HorizontalDivider")
            .assertCountEquals(items.size)
    }

}
