package nextstep.github.util

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlin.time.Duration

suspend fun <T> Flow<T>.toListDuring(
    duration: Duration,
): List<T> = coroutineScope {
    val result = mutableListOf<T>()
    val job = launch {
        this@toListDuring.collect(result::add)
    }
    delay(duration)
    job.cancel()
    return@coroutineScope result
}
