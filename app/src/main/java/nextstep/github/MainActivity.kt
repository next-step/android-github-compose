package nextstep.github

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import nextstep.github.ui.home.RepositoryList
import nextstep.github.ui.home.RepositoryListViewModel
import nextstep.github.ui.theme.GithubTheme

class MainActivity : ComponentActivity() {
    private val viewModel: RepositoryListViewModel by viewModels { RepositoryListViewModel.Factory }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubTheme {
                val repositoryUiState by viewModel.repositoryUiState.collectAsStateWithLifecycle()
                val repositoryErrorState by viewModel.repositoryErrorState.collectAsStateWithLifecycle()
                RepositoryList(
                    uiState = repositoryUiState,
                    errorState = repositoryErrorState,
                    onRetry = {
                        viewModel.fetchRepositories()
                    },
                )
                LaunchedEffect(Unit) {
                    viewModel.fetchRepositories()
                }
            }
        }
    }
}
