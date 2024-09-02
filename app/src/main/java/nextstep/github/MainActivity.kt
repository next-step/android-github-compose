package nextstep.github

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nextstep.github.ui.screen.GithubRepositoryRoute
import nextstep.github.ui.theme.GithubTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubTheme {
                GithubRepositoryRoute()
            }
        }
    }
}
