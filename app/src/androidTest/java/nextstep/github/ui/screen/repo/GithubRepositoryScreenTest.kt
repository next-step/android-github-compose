package nextstep.github.ui.screen.repo

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import nextstep.github.BaseComposeTest
import nextstep.github.R
import nextstep.github.data.response.RepositoryResponse
import org.junit.Before
import org.junit.Test

class GithubRepositoryScreenTest : BaseComposeTest() {

    private var state = mutableStateOf(GithubState())

    @Before
    fun setup() {
        composeTestRule.setContent {
            GithubRepositoryScreen(
                repositoryItems = state.value.repositories,
                isLoading = state.value.loading,
                snackbarHostState = SnackbarHostState()
            )
        }
    }

    @Test
    fun 로딩_상태에서는_프로그레스바가_보여야한다() {
        state.value = GithubState(loading = true)

        composeTestRule.onNodeWithContentDescription(
            resourceTestRule.getString(R.string.loading_content_description)
        ).assertIsDisplayed()
    }

    @Test
    fun 로딩_상태가_아니라면_프로그레스바가_보이지않아야한다() {
        state.value = GithubState(loading = true)

        composeTestRule.onNodeWithContentDescription(
            resourceTestRule.getString(R.string.loading_content_description)
        ).assertIsDisplayed()
    }

    @Test
    fun 보여줄_레포지토리가_없다면_빈_안내화면이_보여야한다() {
        state.value = GithubState(repositories = emptyList(), loading = false)

        composeTestRule.onNodeWithText(
            resourceTestRule.getString(R.string.empty_section_title),
        ).assertIsDisplayed()
    }

    @Test
    fun 보여줄_레포지토리가_로딩과_빈화면이_보이지_않아야_한다() {
        state.value = GithubState(repositories = listOf(RepositoryResponse()), loading = false)

        composeTestRule.onNodeWithText(
            resourceTestRule.getString(R.string.empty_section_title),
        ).assertIsNotDisplayed()
        composeTestRule.onNodeWithContentDescription(
            resourceTestRule.getString(R.string.loading_content_description)
        ).assertIsNotDisplayed()
    }
}


