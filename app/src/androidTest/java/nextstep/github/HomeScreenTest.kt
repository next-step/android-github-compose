package nextstep.github

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import nextstep.github.domain.model.dummyDefaultGithubRepo
import nextstep.github.domain.model.dummyGithubRepoHot
import nextstep.github.domain.model.dummyGithubRepoNonHot
import nextstep.github.ui.home.HomeScreen
import nextstep.github.ui.home.HomeUiState
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private var homeUiState by mutableStateOf<HomeUiState>(HomeUiState.Loading)

    @Before
    fun setup() {
        composeTestRule.setContent {
            HomeScreen(
                homeUiState = homeUiState,
                onRetry = { }
            )
        }
    }

    @Test
    fun 목록이_로딩되기_전에는_로딩_UI를_노출한다() {
        homeUiState = HomeUiState.Loading

        composeTestRule
            .onNodeWithTag("FullScreenLoading")
            .assertIsDisplayed()
    }

    @Test
    fun 데이터를_정상적으로_받으면_리스트에_노출된다() {
        homeUiState = HomeUiState.HasRepos(listOf(dummyDefaultGithubRepo))
        composeTestRule
            .onNodeWithText(dummyDefaultGithubRepo.fullName)
            .assertIsDisplayed()
    }

    @Test
    fun 저장소의_star_개수가_50개_이상이면_HOT_텍스트를_노출한다() {
        homeUiState = HomeUiState.HasRepos(listOf(dummyGithubRepoHot))
        composeTestRule
            .onNodeWithText("HOT")
            .assertIsDisplayed()
    }

    @Test
    fun 저장소의_star_개수가_50개_미만이면_HOT_텍스트를_노출하지_않는다() {
        homeUiState = HomeUiState.HasRepos(listOf(dummyGithubRepoNonHot))
        composeTestRule
            .onNodeWithText("HOT")
            .assertIsNotDisplayed()
    }

    @Test
    fun 데이터를_가져오지_못하면_스낵바가_노출된다() {
        homeUiState = HomeUiState.Error

        composeTestRule
            .onNodeWithText("예상치 못한 오류가 발생했습니다.")
            .assertIsDisplayed()
    }

    @Test
    fun 목록이_빈_경우에는_빈_화면_UI를_노출한다() {
        homeUiState = HomeUiState.Empty

        composeTestRule
            .onNodeWithText("목록이 비었습니다.")
            .assertIsDisplayed()
    }

}
