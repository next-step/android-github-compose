package nextstep.github

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.isDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.github.ui.home.HomeScreen
import nextstep.github.ui.home.HomeUiState
import nextstep.github.ui.home.model.dummyData
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private var homeUiState by mutableStateOf<HomeUiState>(HomeUiState.HasRepos(dummyData))

    @Before
    fun setup() {
        composeTestRule.setContent {
            HomeScreen(homeUiState = homeUiState)
        }
    }

    @Test
    fun 데이터를_정상적으로_받으면_리스트에_노출된다() {
        composeTestRule
            .onNodeWithText("next-step")
            .isDisplayed()
    }

    @Test
    fun 데이터를_가져오지_못하면_에러_텍스트가_노출된다() {
        homeUiState = HomeUiState.NoRepos("next-step Repositories not found")
        composeTestRule
            .onNodeWithText("next-step Repositories not found")
            .isDisplayed()
    }

}
