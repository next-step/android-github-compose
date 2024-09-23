package nextstep.github.ui.github


import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import nextstep.github.model.GithubRepo
import nextstep.github.ui.theme.GithubTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GithubRepositoryListScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 로딩중일때_LoadingIndicator가_나온다() {
        // Given
        val uiState = GithubRepoUiState(isLoading = true)

        // When
        composeTestRule.setContent {
            GithubTheme {
                GithubRepositoryListScreen(
                    uiState = uiState, snackBarHostState = SnackbarHostState()
                )
            }
        }

        // Then
        composeTestRule.onNodeWithTag("LoadingIndicator").assertExists()
    }

    @Test
    fun 저장소를_성공적으로_가져왔을때_저장소리스트가_보여진다() {
        // Given
        val repositories = listOf(
            GithubRepo(1, "NextStep/Test", "테스트 저장소"),
            GithubRepo(2, "NextStep/Test2", "테스트 저장소2"),
            GithubRepo(3, "NextStep/Test3", "테스트 저장소3")
        )
        val uiState = GithubRepoUiState(
            isLoading = false, repositories = repositories
        )

        // When
        composeTestRule.setContent {
            GithubTheme {
                GithubRepositoryListScreen(
                    uiState = uiState, snackBarHostState = SnackbarHostState()
                )
            }
        }

        // Then
        repositories.forEach { repo ->
            composeTestRule.onNodeWithText(repo.fullName).assertExists()
            val description = repo.description
            if (description != null) {
                composeTestRule.onNodeWithText(description).assertExists()
            }
        }
    }

    @Test
    fun 에러가_발생하면_스낵바가_나온다() {
        // Given
        val uiState = GithubRepoUiState()

        // When
        composeTestRule.setContent {
            val snackBarHostState = remember { SnackbarHostState() }
            LaunchedEffect(true) {
                snackBarHostState.showSnackbar(
                    message = "에러 메세지", actionLabel = "재시도"
                )
            }
            GithubTheme {
                GithubRepositoryListScreen(
                    uiState = uiState, snackBarHostState = snackBarHostState
                )
            }
        }

        // Then
        composeTestRule.waitForIdle()
        composeTestRule.onNodeWithText("에러 메세지").assertExists()
        composeTestRule.onNodeWithText("재시도").assertExists()

    }
}

