package nextstep.github.ui.repository

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels

class RepositoryListActivity : ComponentActivity() {

    private val viewModel: RepositoryListViewModel by viewModels { RepositoryListViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            RepositoryListScreen(viewModel = viewModel)
        }
    }

}
