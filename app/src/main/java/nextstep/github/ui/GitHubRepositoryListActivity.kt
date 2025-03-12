package nextstep.github.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import nextstep.github.ui.list.GitHubRepositoryListScreen
import nextstep.github.ui.list.GitHubRepositoryListViewModel
import nextstep.github.ui.theme.GithubTheme

class GitHubRepositoryListActivity : ComponentActivity() {
    private val viewModel: GitHubRepositoryListViewModel by viewModels { GitHubRepositoryListViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            GithubTheme {
                GitHubRepositoryListScreen(
                    viewModel = viewModel,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
