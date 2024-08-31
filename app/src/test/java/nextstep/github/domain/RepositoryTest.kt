package nextstep.github.domain

import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.Test

class RepositoryTest {

    @Test
    fun `스타수가 50미만인 경우 hot이 노출이 되지 않는다`(): Unit = runTest {
        val actual = Repository("123", "456", 49)
        Truth.assertThat(actual.isHot).isFalse()
    }

    @Test
    fun `스타수가 50이상인 경우 hot이 노출이 된다`(): Unit = runTest {
        val actual = Repository("123", "456", 50)
        Truth.assertThat(actual.isHot).isTrue()
    }
}