package nextstep.github.ui.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.di.AppContainer
import nextstep.github.ui.viewmodel.GithubViewModel

@Composable
fun GithubApp(
    appContainer: AppContainer,
    modifier: Modifier = Modifier
) {
    val viewModel: GithubViewModel = viewModel(
        factory = GithubViewModel.provideFactory(appContainer.githubRepository)
    )
}
