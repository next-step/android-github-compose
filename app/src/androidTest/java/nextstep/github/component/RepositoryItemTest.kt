package nextstep.github.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
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

    @Test
    fun `레포지토리_스타의_갯수를_표현하는_별아이콘이_올바르게_보여야_한다`() {
        //given
        val item = Repository(
            id = 55,
            fullName = "next-step/nextstep-docs55",
            description = "nextstep 매뉴얼 및 문서를 관리하는 저장소65",
            stars = 41
        )

        //when
        composeTestRule.setContent {
            RepositoryItem(item)
        }

        //then
        composeTestRule
            .onNodeWithContentDescription("star")
            .assertIsDisplayed()
    }

    @Test
    fun `레포지토리_스타의_갯수가_올바르게_보여야_한다`() {
        //given
        val item = Repository(
            id = 111,
            fullName = "next-step/nextstep-docs111",
            description = "nextstep 매뉴얼 및 문서를 관리하는 저장소222",
            stars = 15
        )

        //when
        composeTestRule.setContent {
            RepositoryItem(item)
        }

        //then
        composeTestRule
            .onNodeWithText("15")
            .assertIsDisplayed()

    }

    @Test
    fun `50개_이상의_스타의_갯수의_레포지토리는_HOT_라벨이_보여야_한다`() {
        //given
        val item = Repository(
            id = 53,
            fullName = "next-step/nextstep-docs53",
            description = "nextstep 매뉴얼 및 문서를 관리하는 저장소53",
            stars = 53
        )

        //when
        composeTestRule.setContent {
            RepositoryItem(item)
        }

        //then
        composeTestRule
            .onNodeWithText("HOT")
            .assertIsDisplayed()
    }

    @Test
    fun `50개_미만의_스타의_갯수의_레포지토리는_HOT_라벨이_보이지_않아야_한다`() {
        //given
        val item = Repository(
            id = 25,
            fullName = "next-step/nextstep-docs25",
            description = "nextstep 매뉴얼 및 문서를 관리하는 저장소25",
            stars = 48
        )

        //when
        composeTestRule.setContent {
            RepositoryItem(item)
        }

        //then
        composeTestRule
            .onNodeWithText("HOT")
            .assertIsNotDisplayed()
    }
}