import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.github.data.model.RepositoryModel
import nextstep.github.ui.screen.github.GithubScreen
import org.junit.Rule
import org.junit.Test

class GithubScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    val repositoryList = listOf(
        RepositoryModel(
            id = 1,
            fullName = "next-step/nextstep-docs",
            description = "nextstep 매뉴얼 및 문서를 관리하는 저장소",
        ),
        RepositoryModel(
            id = 2,
            fullName = "next-step/holy-moly",
            description = "nextstep 홀리몰리한 저장소",
        ),
        RepositoryModel(
            id = 3,
            fullName = "next-step/haly-galy",
            description = "nextstep 할리갈리한 저장소",
        ),
    )

    @Test
    fun Github_레포지토리_데이터가_정상적으로_화면에_출력된다() {
        composeTestRule.setContent {
            GithubScreen(
                repositoryList = repositoryList,
            )
        }

        composeTestRule.onNodeWithText("next-step/nextstep-docs")
            .assertExists()

        composeTestRule.onNodeWithText("next-step/holy-moly")
            .assertExists()

        composeTestRule.onNodeWithText("next-step/haly-galy")
            .assertExists()
    }
}
