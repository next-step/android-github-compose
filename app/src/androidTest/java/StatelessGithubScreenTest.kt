import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import nextstep.github.ui.screen.github.GithubScreen
import nextstep.github.ui.screen.github.RepositoryUiState
import nextstep.github.ui.uistate.UiState
import org.junit.Rule
import org.junit.Test

class StatelessGithubScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    val uiState = UiState.Success(
        data = listOf(
            RepositoryUiState(
                id = 1,
                fullName = "next-step/nextstep-docs",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소",
            ),
            RepositoryUiState(
                id = 2,
                fullName = "next-step/holy-moly",
                description = "nextstep 홀리몰리한 저장소",
            ),
            RepositoryUiState(
                id = 3,
                fullName = "next-step/haly-galy",
                description = "nextstep 할리갈리한 저장소",
            ),
        )
    )

    @Test
    fun Github_레포지토리_데이터가_정상적으로_화면에_출력된다() {
        composeTestRule.setContent {
            GithubScreen(
                repositoryUiState = uiState,
                snackbarHostState = SnackbarHostState()
            )
        }

        uiState.data.forEach {
            composeTestRule.onNodeWithText(it.fullName)
                .assertExists()
        }
    }

    @Test
    fun Github_레포지토리_데이터가_빈값일시_화면에_메시지가_출력된다() {
        composeTestRule.setContent {
            GithubScreen(
                repositoryUiState = UiState.Empty,
                snackbarHostState = SnackbarHostState()
            )
        }

        composeTestRule.onAllNodesWithText("목록이 비었습니다.")
            .onFirst()
            .assertExists()
    }

    @Test
    fun Github_레포지토리_데이터가_로딩일시_로딩바가_화면에_출력된다() {
        composeTestRule.setContent {
            GithubScreen(
                repositoryUiState = UiState.Loading,
                snackbarHostState = SnackbarHostState()
            )
        }

        composeTestRule.onNodeWithContentDescription("LoadingProgressBar")
            .assertIsDisplayed()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun 오류_발생시_화면에_스낵바가_호출된다() = runTest {

        val snackbarHostState = SnackbarHostState()

        composeTestRule.setContent {
            GithubScreen(
                repositoryUiState = UiState.Empty,
                snackbarHostState = snackbarHostState
            )
        }

        launch {
            snackbarHostState.showSnackbar(
                message = "예상치 못한 오류가 발생했습니다.",
                actionLabel = "재시도",
            )
        }

        advanceUntilIdle()

        composeTestRule.onNodeWithContentDescription("Snackbar")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("예상치 못한 오류가 발생했습니다.")
            .assertIsDisplayed()

        snackbarHostState.currentSnackbarData?.dismiss()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun 스낵바_재시도_버튼을_클릭하면_스낵바가_사라진다() = runTest {
        val snackbarHostState = SnackbarHostState()

        composeTestRule.setContent {
            GithubScreen(
                repositoryUiState = UiState.Empty,
                snackbarHostState = snackbarHostState
            )
        }

        launch {
            when (snackbarHostState.showSnackbar(
                message = "예기치 못한 오류",
                actionLabel = "재시도"
            )) {
                SnackbarResult.ActionPerformed -> {
                    snackbarHostState.currentSnackbarData?.dismiss()
                }

                else -> {}
            }
        }

        advanceUntilIdle()

        snackbarHostState.currentSnackbarData?.performAction()

        advanceUntilIdle()

        composeTestRule.onNodeWithContentDescription("Snackbar")
            .assertIsNotDisplayed()
    }
}
