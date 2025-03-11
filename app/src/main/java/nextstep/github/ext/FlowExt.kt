package nextstep.github.ext

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collectLatest


@Composable
fun <T> Flow<T>.ToLaunchedEffect(action: suspend (T) -> Unit) {
    val flow = this
    LaunchedEffect(flow) {
        collectLatest(action)
    }
}