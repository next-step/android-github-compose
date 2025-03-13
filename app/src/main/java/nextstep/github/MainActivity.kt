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
            LaunchedEffect(key1 = Unit) {
                val data = repository.getRepositories(organization = Const.NEXT_STEP)
                Log.d("MainActivity", data.toString())
            }

            GithubTheme {

            }
        }
    }
}
