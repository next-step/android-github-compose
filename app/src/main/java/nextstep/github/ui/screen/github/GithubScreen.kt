package nextstep.github.ui.screen.github

import androidx.compose.foundation.background
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun GithubScreen(
    modifier: Modifier = Modifier,
    viewModel: GithubViewModel = viewModel(factory = GithubViewModel.Companion.Factory),
) {
    Text(
        text = "Github",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surface)
    )
}
