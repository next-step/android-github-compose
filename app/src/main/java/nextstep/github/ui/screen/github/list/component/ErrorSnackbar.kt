package nextstep.github.ui.screen.github.list.component

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun ErrorSnackbar(
    errorMessage: String,
    actionString: String,
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
    onClickAction: () -> Unit
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        SnackbarHost(
            hostState = snackbarHostState,
        )
    }

    LaunchedEffect(snackbarHostState) {
        val result = snackbarHostState.showSnackbar(
            message = errorMessage,
            actionLabel = actionString,
            duration = SnackbarDuration.Long
        )

        when (result) {
            SnackbarResult.Dismissed -> {
                Log.d("snackBar", "snackBar: 스낵바 닫아짐")
            }

            SnackbarResult.ActionPerformed -> {
                onClickAction()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ErrorSnackbarPreview() {
    val snackbarHostState = remember { SnackbarHostState() }

    ErrorSnackbar(
        errorMessage = "Error",
        actionString = "Retry",
        snackbarHostState = snackbarHostState,
        modifier = Modifier,
        onClickAction = {}
    )
}
