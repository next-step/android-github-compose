package nextstep.github.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import nextstep.github.ui.theme.GithubTheme
import nextstep.github.viewmodel.RepositoryListViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: RepositoryListViewModel by viewModels { RepositoryListViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GithubTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RepositoryListScreen(viewModel = viewModel)
                }
            }
        }
    }
}
