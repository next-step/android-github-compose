package nextstep.github.ui.github

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import nextstep.github.GithubApplication
import nextstep.github.ui.theme.GithubTheme

class GithubRepoListActivity : ComponentActivity() {
    private val tag = "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = (application as GithubApplication).appContainer
        lifecycleScope.launch {
            val repository = appContainer.githubRepository
            val response = repository.getRepositories("next-step")
            Log.d(tag, "response: ${response.joinToString("\n")}")
        }
        setContent {
            GithubTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GithubRepositoryListScreen()
                }
            }
        }
    }
}


