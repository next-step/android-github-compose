package nextstep.github

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import nextstep.github.data.repository.GithubRepository
import nextstep.github.ui.theme.GithubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = (application as MainApplication).appContainer
        initObservers(appContainer.githubRepository)

        setContent {
            GithubTheme { }
        }
    }

    private fun initObservers(repository : GithubRepository){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                repository.getRepositories("next-step")
                    .catch { e ->
                        Log.e("MainActivity",e.toString())
                    }.collect { repositories ->
                        Log.d("MainActivity",repositories.joinToString("\n"))
                    }
            }
        }
    }
}
