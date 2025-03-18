package nextstep.github.github.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.ui.GithubScreen
import nextstep.github.ui.GithubUiEvent
import nextstep.github.ui.GithubUiState
import nextstep.github.ui.GithubViewModel
import org.junit.Rule
import org.junit.Test

class GithubScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var githubUiState by mutableStateOf<GithubUiState>(GithubUiState.Loading)

    private lateinit var githubViewModel: GithubViewModel

    @Test
    fun 로딩중_상태일_때에는_로딩_UI를_노출한다() {
        composeTestRule.setContent {
            GithubScreen(
                uiState = githubUiState,
            )
        }
        githubUiState = GithubUiState.Loading
        composeTestRule.onNodeWithTag("LoadingBox").assertIsDisplayed()
    }

    @Test
    fun 빈_목록_상태일_때에는_빈_화면_UI를_노출한다() {
        composeTestRule.setContent {
            GithubScreen(
                uiState = githubUiState,
            )
        }
        githubUiState = GithubUiState.Empty
        composeTestRule.onNodeWithTag("EmptyRepositoryBox").assertIsDisplayed()
    }

    @Test
    fun 에러_이벤트가_발생했을_때에는_재시도_가능한_스낵바를_노출한다() {
        composeTestRule.setContent {
            githubViewModel = viewModel(factory = GithubViewModel.Factory)
            GithubScreen(viewModel = githubViewModel)
        }

        composeTestRule.runOnIdle {
            githubViewModel.sendUiEvent(GithubUiEvent.ShowErrorSnackBar)
        }

        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("예상치 못한 오류가 발생했습니다.").assertIsDisplayed()
    }

}