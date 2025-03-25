package nextstep.github

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import nextstep.github.ui.screen.RepositoryListScreen
import nextstep.github.ui.theme.GithubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = (application as GithubApplication).appContainer
        val repository = appContainer.githubRepository

        lifecycleScope.launch {
            val repository = repository.getRepositories("next-step")
            Log.d("repository", repository.toString())
        }

        setContent {
            GithubTheme {
                RepositoryListScreen(
                    repositories = emptyList(),
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
