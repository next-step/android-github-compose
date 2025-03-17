package nextstep.github

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appContainer = (application as App).appContainer
        val githubRepository = appContainer.githubRepository

        lifecycleScope.launch {
            val repositories = githubRepository.getRepositories("next-step")
            Log.d("Test", "repositories > $repositories")
        }
    }
}