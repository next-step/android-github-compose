package nextstep.github.ui.nextsteprepos

import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import nextstep.github.model.GithubRepo
import org.junit.Rule
import org.junit.Test

class NextStepReposScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 저장소_목록이_화면에_보인다() {
        // given
        val repos = List(3) { index ->
            val number = index + 1
            GithubRepo(
                fullName = "next-step/nextstep-docs-$number",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소-$number"
            )
        }
        val uiState = NextStepReposUiState(
            isLoading = false,
            nextStepRepos = repos
        )

        composeTestRule.setContent {
            NextStepReposScreen(
                uiState = uiState,
                snackbarHostState = SnackbarHostState()
            )
        }

        // then: nextstepRepo의 fullName과 description이 화면에 노출되어야 함
        (1..3).forEach { number ->
            composeTestRule.onNodeWithText("next-step/nextstep-docs-$number").assertIsDisplayed()
            composeTestRule.onNodeWithText("nextstep 매뉴얼 및 문서를 관리하는 저장소-$number")
                .assertIsDisplayed()
        }

        // then: 로딩이 보이지 않는다.
        composeTestRule.onNodeWithTag("loading_indicator").assertDoesNotExist()
    }

    @Test
    fun NextStep_저장소_목록을_바닥으로_스크롤하면_마지막_저장소가_보인다() {
        // given
        val repos = List(100) { index ->
            val number = index + 1
            GithubRepo(
                fullName = "next-step/nextstep-docs-$number",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소-$number"
            )
        }
        val uiState = NextStepReposUiState(
            isLoading = false,
            nextStepRepos = repos
        )

        composeTestRule.setContent {
            NextStepReposScreen(
                uiState = uiState,
                snackbarHostState = SnackbarHostState()
            )
        }

        // when: 마지막 순번으로 스크롤하면
        composeTestRule.onNodeWithTag("repo_list").performScrollToIndex(99)

        // then: 마지막 저장소의 정보가 화면에 노출되어야 함
        composeTestRule.onNodeWithText("next-step/nextstep-docs-100").assertIsDisplayed()
        composeTestRule.onNodeWithText("nextstep 매뉴얼 및 문서를 관리하는 저장소-100").assertIsDisplayed()

        // then: 로딩이 보이지 않는다.
        composeTestRule.onNodeWithTag("loading_indicator").assertDoesNotExist()
    }

    @Test
    fun 저장소_목록이_비어있을_때_화면에_보인다() {
        // given
        val uiState = NextStepReposUiState(
            isLoading = false,
            nextStepRepos = emptyList()
        )

        composeTestRule.setContent {
            NextStepReposScreen(
                uiState = uiState,
                snackbarHostState = SnackbarHostState()
            )
        }

        // then: 저장소 목록이 비어있을 때 "저장소가 없습니다" 메시지가 화면에 노출되어야 함
        composeTestRule.onNodeWithText("목록이 비었습니다.").assertIsDisplayed()
        // then: 로딩이 보이지 않는다.
        composeTestRule.onNodeWithTag("loading_indicator").assertDoesNotExist()
    }

    @Test
    fun 로딩중이면_로딩바가_보인다() {
        // given
        val uiState = NextStepReposUiState(isLoading = true)

        composeTestRule.setContent {
            NextStepReposScreen(
                uiState = uiState,
                snackbarHostState = SnackbarHostState()
            )
        }

        // then: 로딩이 보이지 않는다.
        composeTestRule.onNodeWithTag("loading_indicator").assertIsDisplayed()
    }
}