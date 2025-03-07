package nextstep.github.ui.screen.github

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.github.di.AppContainer

@Composable
fun GithubApp(
    appContainer: AppContainer,
    modifier: Modifier = Modifier
) {
    val viewModel: GithubViewModel = viewModel(
        factory = GithubViewModel.provideFactory(appContainer.githubRepository)
    )

    GithubScreen(
        viewModel = viewModel,
        modifier = modifier,
    )
}

@Composable
fun GithubScreen(
    viewModel: GithubViewModel,
    modifier: Modifier = Modifier
) {

}
