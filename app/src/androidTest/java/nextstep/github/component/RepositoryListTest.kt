package nextstep.github.component

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithText
import nextstep.github.data.api.model.RepositoryEntity
import nextstep.github.ui.github.component.RepositoryList
import org.junit.Rule
import org.junit.Test

class RepositoryListTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `여러개의_레포지토리의_이름이_올바르게_보여야_한다`() {
        //given
        val items = listOf(
            RepositoryEntity(
                fullName = "next-step/nextstep-docs1",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소1"
            ),
            RepositoryEntity(
                fullName = "next-step/nextstep-docs2",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소2"
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
            RepositoryEntity(
                fullName = "next-step/nextstep-docs3",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소3"
            ),
            RepositoryEntity(
                fullName = "next-step/nextstep-docs4",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소4"
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
            RepositoryEntity(
                fullName = "next-step/nextstep-docs1",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소1"
            ),
            RepositoryEntity(
                fullName = "next-step/nextstep-docs2",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소2"
            ),
            RepositoryEntity(
                fullName = "next-step/nextstep-docs3",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소3"
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
