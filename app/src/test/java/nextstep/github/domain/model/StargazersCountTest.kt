package nextstep.github.domain.model

import org.junit.Test

class StargazersCountTest {
    @Test
    fun `star 개수가 50 이상이면 인기있는 것이다`() {
        // given
        val stargazersCount = StargazersCount(50)

        // when
        val isPopular = stargazersCount.isPopular

        // then
        assert(isPopular)
    }

    @Test
    fun `star 개수가 50 미만이면 인기있는 것이 아니다`() {
        // given
        val stargazersCount = StargazersCount(49)

        // when
        val isPopular = stargazersCount.isPopular

        // then
        assert(!isPopular)
    }
}
