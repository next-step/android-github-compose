package nextstep.github.ui.component

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import nextstep.github.R

@Composable
internal fun GithubRepositorySnackBar(
    snackBarHostState: SnackbarHostState,
    onRetryAction: () -> Unit,
) {
    val message = stringResource(R.string.github_repositories_error)
    val actionLabel = stringResource(R.string.retry_action)

    LaunchedEffect(Unit) {
        snackBarHostState.showSnackbar(
            message = message,
            actionLabel = actionLabel,
            duration = SnackbarDuration.Indefinite,
            withDismissAction = false,
        ).also { result ->
            if (result == SnackbarResult.ActionPerformed) {
                onRetryAction.invoke()
            }
        }
    }
}