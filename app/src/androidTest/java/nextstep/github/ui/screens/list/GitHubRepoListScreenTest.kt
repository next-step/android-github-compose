package nextstep.github.ui.screens.list

import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performScrollToIndex
import nextstep.github.data.repositories.impls.FakeGithubRepoRepository
import org.junit.Rule
import org.junit.Test

class GitHubRepoListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val snackBarHostState = SnackbarHostState()

    @Test
    fun 로딩상태일_때_로딩_컴포넌트가_표시된다() {
        // given
        composeTestRule
            .setContent {
                GitHubRepoListScreen(
                    state = GitHubRepoListUiState.Loading,
                    snackBarHostState = snackBarHostState,
                )
            }

        // then
        composeTestRule
            .onNodeWithTag(testTag = "loading")
            .assertIsDisplayed()
    }

    @Test
    fun 레포지토리가_없을_때_빈_화면_UI를_표시한다() {
        // given
        composeTestRule
            .setContent {
                GitHubRepoListScreen(
                    state = GitHubRepoListUiState.Empty,
                    snackBarHostState = snackBarHostState,
                )
            }

        // then
        composeTestRule
            .onNodeWithText("목록이 비었습니다.")
            .assertIsDisplayed()
    }

    @Test
    fun 레포지토리들을_성공적으로_불러왔을떄_레포지토리_목록을_표시한다() {
        // given
        composeTestRule
            .setContent {
                GitHubRepoListScreen(
                    state = GitHubRepoListUiState.Success(
                        repositories = FakeGithubRepoRepository.gitHubRepos
                    ),
                    snackBarHostState = snackBarHostState,
                )
            }

        // then
        FakeGithubRepoRepository.gitHubRepos.forEachIndexed { index, _ ->
            composeTestRule
                .onNodeWithText("next-step/nextstep-docs-$index")
                .assertIsDisplayed()

            composeTestRule.onNodeWithTag("repositoryList")
                .performScrollToIndex(index)
        }
    }
}
