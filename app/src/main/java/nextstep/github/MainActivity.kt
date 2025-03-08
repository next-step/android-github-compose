package nextstep.github

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import nextstep.github.ui.GithubRepositoryScreen
import nextstep.github.ui.GithubRepositoryViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: GithubRepositoryViewModel by viewModels<GithubRepositoryViewModel> { GithubRepositoryViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GithubRepositoryScreen(viewModel = viewModel)
        }
    }
}
