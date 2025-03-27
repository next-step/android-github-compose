package nextstep.github.ui.nextsteprepos

import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import nextstep.github.domain.model.GithubRepo
import nextstep.github.domain.model.StargazersCount
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
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소-$number",
                stargazersCount = StargazersCount(30),
            )
        }
        val uiState = NextStepReposUiState(
            uiState = UiState.Success,
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
        composeTestRule.onNodeWithTag("loading_indicator").assertIsNotDisplayed()
    }

    @Test
    fun NextStep_저장소_목록을_바닥으로_스크롤하면_마지막_저장소가_보인다() {
        // given
        val repos = List(100) { index ->
            val number = index + 1
            GithubRepo(
                fullName = "next-step/nextstep-docs-$number",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소-$number",
                stargazersCount = StargazersCount(30),
            )
        }
        val uiState = NextStepReposUiState(
            uiState = UiState.Success,
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
        composeTestRule.onNodeWithTag("loading_indicator").assertIsNotDisplayed()
    }

    @Test
    fun 저장소_목록이_비어있을_때_화면에_목로_보인다() {
        // given
        val uiState = NextStepReposUiState(
            uiState = UiState.Success,
            nextStepRepos = emptyList()
        )

        composeTestRule.setContent {
            NextStepReposScreen(
                uiState = uiState,
                snackbarHostState = SnackbarHostState()
            )
        }

        // then
        composeTestRule.onNodeWithText("목록이 비었습니다.").assertIsDisplayed()
        // then: 로딩이 보이지 않는다.
        composeTestRule.onNodeWithTag("loading_indicator").assertIsNotDisplayed()
    }

    @Test
    fun 로딩중이면_로딩바가_보인다() {
        // given
        val uiState = NextStepReposUiState(uiState = UiState.Loading)

        composeTestRule.setContent {
            NextStepReposScreen(
                uiState = uiState,
                snackbarHostState = SnackbarHostState()
            )
        }

        // then: 로딩이 보인다.
        composeTestRule.onNodeWithTag("loading_indicator").assertIsDisplayed()
    }

    @Test
    fun 별이_50개_이상이면_HOT이_보인다() {
        // Given: 별이 50개인 인기 저장소
        val popularRepo = GithubRepo(
            fullName = "next-step/nextstep-docs",
            description = "nextstep 매뉴얼 및 문서를 관리하는 저장소",
            stargazersCount = StargazersCount(50)
        )
        val uiState = NextStepReposUiState(
            uiState = UiState.Success,
            nextStepRepos = listOf(popularRepo)
        )

        // When: NextStepReposScreen을 렌더링
        composeTestRule.setContent {
            NextStepReposScreen(
                uiState = uiState,
                snackbarHostState = SnackbarHostState()
            )
        }

        // Then: "HOT" 레이블이 표시되어야 함
        composeTestRule.onNodeWithText("HOT").assertIsDisplayed()
        // Then: 별 개수가 표시되어야 함
        composeTestRule.onNodeWithText("50").assertIsDisplayed()
    }

    @Test
    fun 별이_50개_미만이면_HOT이_보이지_않는다() {
        // Given: 별이 50개 미만인 인기 저장소
        val notPopularRepo = GithubRepo(
            fullName = "next-step/nextstep-docs",
            description = "nextstep 매뉴얼 및 문서를 관리하는 저장소",
            stargazersCount = StargazersCount(49)
        )
        val uiState = NextStepReposUiState(
            uiState = UiState.Success,
            nextStepRepos = listOf(notPopularRepo)
        )

        // When: NextStepReposScreen을 렌더링
        composeTestRule.setContent {
            NextStepReposScreen(
                uiState = uiState,
                snackbarHostState = SnackbarHostState()
            )
        }

        // Then: "HOT" 레이블이 표시되지 않아야 함
        composeTestRule.onNodeWithText("HOT").assertIsNotDisplayed()
        // Then: 별 개수가 표시되어야 함
        composeTestRule.onNodeWithText("49").assertIsDisplayed()
    }
}