package nextstep.github.model

import junit.framework.TestCase.assertEquals
import org.junit.Test

class GitHubRepoTest {
    @Test
    fun `깃허브 레포의 스타가 50개 이상이면 HOT 레포다`() {
        // given
        val gitHubRepo = GitHubRepo(
            id = 8712,
            fullName = "Retrofit",
            description = null,
            stars = 50,
        )

        // when
        val actual = gitHubRepo.isHot

        // then
        assertEquals(true, actual)
    }

    @Test
    fun `깃허브 레포의 스타가 50개보다 적다면 HOT 레포가 아니다`() {
        // given
        val gitHubRepo = GitHubRepo(
            id = 8712,
            fullName = "Retrofit",
            description = null,
            stars = 49,
        )

        // when
        val actual = gitHubRepo.isHot

        // then
        assertEquals(false, actual)
    }
}
