package nextstep.github.ui.repo

import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import nextstep.github.core.model.RepositoryEntity
import org.junit.Rule
import org.junit.Test

class GithubRepoScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 깃헙_저장소_목록을_화면에_보여준다() {
        // given
        val repositories =
            listOf(
                RepositoryEntity("nextstep/compose", "갓뮤지님의 강의"),
                RepositoryEntity("nextstep/kotlin-tdd", "Jason님의 강의"),
            )

        composeTestRule.setContent {
            GithubRepoScreen(
                repositories = repositories,
            )
        }

        // then
        composeTestRule
            .onAllNodesWithTag("GithubRepoCard")
            .assertCountEquals(repositories.size)
    }
}
