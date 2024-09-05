package nextstep.github.ui.model

import org.junit.Assert
import org.junit.Test

class UiRepositoryTest {

    @Test
    fun `UiRepository의_stars가_50이상이면_isHot은_true여야_한다`() {
        // given
        val repository = UiRepository(
            fullName = "",
            description = null,
            stars = 50
        )

        // when

        // then
        Assert.assertEquals(repository.isHot, true)
    }

    @Test
    fun `UiRepository의_stars가_50미만이면_isHot은_false여야_한다`() {
        // given
        val repository = UiRepository(
            fullName = "",
            description = null,
            stars = 49
        )

        // when

        // then
        Assert.assertEquals(repository.isHot, false)
    }

}
