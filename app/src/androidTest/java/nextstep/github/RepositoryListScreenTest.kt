package nextstep.github

import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceTimeBy
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import nextstep.github.domain.entity.Repository
import nextstep.github.ui.RepositoryListScreen
import nextstep.github.ui.model.RepositoryListScreenUiState
import org.junit.Test

class RepositoryListScreenTest : BaseComposableTest() {

    @Test
    fun `레포지토리_목록_데이터가_존재할때_잘_표시하는지_검증`() {
        // Given
        val repositoryList = listOf(
            Repository(
                fullName = "nextstep/github",
                description = "Github Repository for NextStep"
            )
        ).toPersistentList()

        val uiState = RepositoryListScreenUiState.Success(
            repositoryList = repositoryList
        )

        // When
        composeTestRule.setContent {
            RepositoryListScreen(
                uiState = uiState
            )
        }

        // Then
        composeTestRule
            .onNodeWithText("nextstep/github")
            .assertIsDisplayed()
    }

    @Test
    fun `레포지토리_목록_데이터가_비어있을때_화면_검증`() {
        // Given
        val uiState = RepositoryListScreenUiState.Empty

        // When
        composeTestRule.setContent {
            RepositoryListScreen(
                uiState = uiState
            )
        }

        // Then
        composeTestRule
            .onNodeWithText("목록이 비었습니다.")
            .assertIsDisplayed()
    }

    @Test
    fun `레포지토리_목록을_불러오는_중일때_화면_검증`() {
        // Given
        val uiState = RepositoryListScreenUiState.Loading

        // When
        composeTestRule.setContent {
            RepositoryListScreen(
                uiState = uiState
            )
        }

        // Then
        composeTestRule
            .onNodeWithTag("loading_progress")
            .assertIsDisplayed()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `스낵바가_잘_노출되는지_검증`() = runTest {
        // Given
        val snackBarState = SnackbarHostState()

        composeTestRule.setContent {
            RepositoryListScreen(
                uiState = RepositoryListScreenUiState.Loading,
                snackBarHostState = snackBarState,
            )
        }

        // When
        backgroundScope.launch {
            snackBarState.showSnackbar("스낵바 테스트")
        }
        advanceTimeBy(1000)

        // Then
        composeTestRule
            .onNodeWithText("스낵바 테스트")
            .assertIsDisplayed()
    }
}