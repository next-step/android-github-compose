package nextstep.github

import android.util.Log
import io.mockk.every
import io.mockk.mockkStatic

open class BaseTest {
    // unit test에서 Log 클래스를 mock 처리하기 위한 임시방편 함수
    fun mockLogClass() {
        mockkStatic(Log::class)
        every { Log.d(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0
        every { Log.e(any(), any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.v(any(), any()) } returns 0
    }
}
