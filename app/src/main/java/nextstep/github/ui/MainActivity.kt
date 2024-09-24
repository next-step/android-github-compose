package nextstep.github.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import nextstep.github.NextStepApp
import nextstep.github.ui.model.UiGitHubRepoInfo
import nextstep.github.ui.repos.ReposScreen
import nextstep.github.ui.repos.ReposViewModel
import nextstep.github.ui.theme.GithubTheme

internal class MainActivity : ComponentActivity() {

    private val reposViewModel: ReposViewModel by viewModels { ReposViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GithubTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ReposScreen(
                        reposViewModel = reposViewModel
                    )
                }
            }
        }
    }
}
