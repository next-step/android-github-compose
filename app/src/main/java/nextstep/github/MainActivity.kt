package nextstep.github

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import nextstep.github.di.AppContainer
import nextstep.github.di.RepositoryListContainer
import nextstep.github.presentation.repositorylist.RepositoryListViewModel
import nextstep.github.presentation.theme.GithubTheme

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: RepositoryListViewModel
    private lateinit var appContainer: AppContainer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setDi()

        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.response.collect {
                    Log.d("Response", "$it")
                }
            }
        }

        viewModel.getRepositories()

        setContent {
            GithubTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }

    private fun setDi() {
        appContainer = (application as GithubApplication).appContainer

        appContainer.repositoryListContainer = RepositoryListContainer(appContainer.githubRepository)

        viewModel = appContainer.repositoryListContainer!!.repositoryListViewModelFactory.create()
    }

    override fun onDestroy() {
        appContainer.repositoryListContainer = null

        super.onDestroy()
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GithubTheme {
        Greeting("Android")
    }
}
