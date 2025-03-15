package nextstep.github

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {

    private lateinit var githubViewModel: GithubViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        githubViewModel = (application as GithubApplication).appContainer.githubViewModel

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                githubViewModel.githubRepositories
                    .collectLatest {
                        Log.d("asdf", it.toString())
                    }
            }
        }
    }
}
