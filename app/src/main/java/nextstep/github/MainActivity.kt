package nextstep.github

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import nextstep.github.ui.theme.GithubTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appContainer = (application as GithubApplication).appContainer
        val repository = appContainer.githubRepository

        setContent {
            GithubTheme {
                LaunchedEffect(Unit) {
                    repository.getRepositories("next-step").onEach {
                        Log.d("GithubAPIKMH", "${it.fullName} ${it.description}")
                    }
                }
            }
        }
    }
}
