package nextstep.github

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import nextstep.github.ui.RepositoryListScreen
import nextstep.github.ui.RepositoryListViewModel
import nextstep.github.ui.theme.GithubTheme

class MainActivity : ComponentActivity() {

    private val viewModel: RepositoryListViewModel by viewModels { RepositoryListViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubTheme {
                RepositoryListScreen(viewModel = viewModel)
            }
        }
    }
}