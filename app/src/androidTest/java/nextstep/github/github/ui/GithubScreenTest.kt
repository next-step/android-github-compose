package nextstep.github.github.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import nextstep.github.ui.GithubScreen
import nextstep.github.ui.GithubUiState
import nextstep.github.ui.theme.GithubTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GithubScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var githubUiState by mutableStateOf<GithubUiState>(GithubUiState.Loading)

    @Before
    fun setUp() {
        composeTestRule.setContent {
            GithubTheme {
                GithubScreen(
                    uiState = githubUiState
                )
            }
        }
    }

    @Test
    fun 로딩중_상태일_때에는_로딩_UI를_노출한다() {
        githubUiState = GithubUiState.Loading
        composeTestRule.onNodeWithTag("LoadingBox").assertIsDisplayed()
    }

    @Test
    fun 빈_목록_상태일_때에는_빈_화면_UI를_노출한다() {
        githubUiState = GithubUiState.Empty
        composeTestRule.onNodeWithTag("EmptyRepositoryBox").assertIsDisplayed()
    }



}