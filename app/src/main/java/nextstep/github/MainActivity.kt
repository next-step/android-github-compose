package nextstep.github

import android.os.Bundle
import androidx.activity.ComponentActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {
    private val mainScope = MainScope()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = (application as GithubApplication).appContainer
        val repository = appContainer.githubRepository

        mainScope.launch {
            withContext(Dispatchers.IO) {
                repository.getRepositories(NEXT_STEP_ORGANIZATION)
            }
        }
    }
}

private const val NEXT_STEP_ORGANIZATION = "next-step"