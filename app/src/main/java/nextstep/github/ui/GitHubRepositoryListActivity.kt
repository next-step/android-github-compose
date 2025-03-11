package nextstep.github.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import nextstep.github.ui.github.repository.list.GitHubRepositoryListScreen
import nextstep.github.ui.theme.GithubTheme

class GitHubRepositoryListActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            GithubTheme {
                GitHubRepositoryListScreen(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}
