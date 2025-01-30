package nextstep.github.ui.nextstep

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import nextstep.github.ui.theme.GithubTheme
import nextstep.github.viewmodel.NextStepRepositoryListViewModel

class NextStepRepositoryListActivity : ComponentActivity() {
    private val viewModel: NextStepRepositoryListViewModel by viewModels { NextStepRepositoryListViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GithubTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NextStepRepositoryListScreen(viewModel = viewModel)
                }
            }
        }
    }
}
