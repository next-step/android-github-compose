package nextstep.github

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import nextstep.github.model.Repository
import nextstep.github.ui.github.GithubScreen
import nextstep.github.ui.github.GithubUiState
import org.junit.Rule
import org.junit.Test

class GithubScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    @Test
    fun `UiState가_Loading일때_원형_프로그래스가_보여야_한다`() {
        composeTestRule.setContent {
            GithubScreen(
                uiState = GithubUiState.Loading,
            )
        }
        composeTestRule
            .onNodeWithTag("Loading")
            .assertIsDisplayed()
    }

    @Test
    fun `UiState가_EmptyRepository일때_목록이_비었습니다_문구가_보여야_한다`() {
        composeTestRule.setContent {
            GithubScreen(
                uiState = GithubUiState.EmptyRepository,
            )
        }

        composeTestRule
            .onNodeWithText("목록이 비었습니다.")
            .assertIsDisplayed()
    }

    @Test
    fun `UiState가_Repositories일때_주어진_아이템들이_보여야_한다`() {

        val repositories = (1..3).map {
            Repository(
                id = it,
                fullName = "next-step/nextstep-docs${it}",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소${it}"
            )
        }

        composeTestRule.setContent {
            GithubScreen(
                uiState = GithubUiState.Repositories(items = repositories),
            )
        }

        repositories.forEach {
            composeTestRule
                .onNodeWithText(it.fullName.orEmpty())
                .assertIsDisplayed()
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `주어진_스낵바의_메시지가_보여야_한다`() = runTest {

        val snackBarHostState = SnackbarHostState()

        composeTestRule.setContent {
            GithubScreen(
                uiState = GithubUiState.Loading,
                snackBarHostState = snackBarHostState
            )
        }
        val snackBarJob = launch {
            snackBarHostState?.showSnackbar(
                message = "예기치 못한 오류가 발생했습니다.",
                actionLabel = "재시도"
            )
        }

        advanceUntilIdle()

        composeTestRule
            .onNodeWithText("예기치 못한 오류가 발생했습니다.")
            .assertIsDisplayed()

        snackBarJob.cancel()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `주어진_스낵바의_엑션_라벨이_보여야_한다`() = runTest {

        val snackBarHostState = SnackbarHostState()

        composeTestRule.setContent {
            GithubScreen(
                uiState = GithubUiState.Loading,
                snackBarHostState = snackBarHostState
            )
        }
        val snackBarJob = launch {
            snackBarHostState?.showSnackbar(
                message = "오류",
                actionLabel = "재시도"
            )
        }

        advanceUntilIdle()

        composeTestRule
            .onNodeWithText("재시도")
            .assertIsDisplayed()

        snackBarJob.cancel()
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `주어진_스낵바의_엑션_라벨_클릭시_이벤트가_올바르게_전달되어야_한다`() = runTest {

        val snackBarHostState = SnackbarHostState()

        var isSendEvent = false

        composeTestRule.setContent {
            GithubScreen(
                uiState = GithubUiState.Loading,
                snackBarHostState = snackBarHostState
            )
        }
        val snackBarJob = launch {

            when (snackBarHostState?.showSnackbar(
                message = "예기치 못한 오류",
                actionLabel = "재시도"
            )) {
                SnackbarResult.ActionPerformed -> {
                    isSendEvent = true
                }

                else -> {}
            }
        }

        advanceUntilIdle()

        composeTestRule
            .onNodeWithText("재시도")
            .performClick()

        advanceUntilIdle()

        assertTrue(isSendEvent)

        snackBarJob.cancel()
    }
}