package nextstep.github.ui.list

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasScrollAction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import nextstep.github.domain.model.Repository
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GitHubRepositoryListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var uiState: GitHubRepositoryListState by mutableStateOf(GitHubRepositoryListState.Loading)

    @Before
    fun setup() {
        composeTestRule.setContent {
            GitHubRepositoryListScreen(
                uiState = uiState,
            )
        }
    }

    @Test
    fun `리스트_스크린의_타이틀이_보인다`() {
        composeTestRule
            .onNodeWithText("NEXTSTEP Repositories")
            .assertIsDisplayed()
    }

    @Test
    fun `로딩중에는_원형_프로그래스바가_보인다`() {
        uiState = GitHubRepositoryListState.Loading

        composeTestRule
            .onNodeWithTag("CircularProgressIndicator")
            .assertIsDisplayed()
    }

    @Test
    fun `레포지토리가_없는경우_안내_문구가_보인다`() {
        uiState = GitHubRepositoryListState.Empty

        composeTestRule
            .onNodeWithText("목록이 비었습니다.")
            .assertIsDisplayed()
    }

    @Test
    fun `리스트_목록을_보여준다`() {
        val repositories = List(100) {
            Repository(
                id = it,
                fullName = "풀무원말고풀네임$it",
                description = "설탕말고설명$it"
            )
        }
        uiState = GitHubRepositoryListState.Repositories(repositories)

        composeTestRule
            .onNodeWithText("풀무원말고풀네임1")
            .assertIsDisplayed()

        composeTestRule
            .onNode(hasScrollAction())
            .performScrollToIndex(repositories.lastIndex)

        composeTestRule
            .onNodeWithText("풀무원말고풀네임99")
            .assertIsDisplayed()
    }
}
