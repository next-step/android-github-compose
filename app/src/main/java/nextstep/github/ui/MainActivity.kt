package nextstep.github.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import nextstep.github.NextStepApp
import nextstep.github.ui.model.UiGitHubRepoInfo
import nextstep.github.ui.theme.GithubTheme

internal class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val appContainer = (application as NextStepApp).appContainer
        val getGitHubRepositoryUseCase = appContainer.getGitHubRepositoryUseCase
        val repos: Flow<List<UiGitHubRepoInfo>> =
            flow { emit(getGitHubRepositoryUseCase("next-step")) }

        setContent {

            val repo by repos.collectAsStateWithLifecycle(emptyList())

            GithubTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LazyColumn {
                        items(repo) { r ->
                            Column {
                                Text(text = r.fullName)
                                Text(text = r.description)
                            }
                        }
                    }
                }
            }
        }
    }
}
