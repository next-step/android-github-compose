package nextstep.github.ui.screen

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nextstep.github.MainApplication
import nextstep.github.ui.screen.github.GithubApp
import nextstep.github.ui.theme.GithubTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val appContainer = (application as MainApplication).appContainer

        setContent {
            GithubTheme {
                GithubApp(
                    appContainer = appContainer
                )
            }
        }
    }
}
