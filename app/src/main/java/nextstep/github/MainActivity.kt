package nextstep.github

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nextstep.github.ui.theme.GithubTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fetchTest()
        setContent {
            GithubTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                }
            }
        }
    }

    private fun fetchTest() {
        val appContainer = (application as GithubApplication).appContainer
        val repository = appContainer.githubRepository

        CoroutineScope(Dispatchers.IO).launch {
            val repos = repository.getRepositories("next-step")
            Log.d("sana", "repos = $repos")
        }
    }

}
