package nextstep.github.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import nextstep.github.ui.github.GithubScreen
import nextstep.github.ui.github.GithubViewModel
import nextstep.github.ui.theme.GithubTheme

class GithubActivity : ComponentActivity() {

    private val viewModel: GithubViewModel by viewModels { GithubViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubTheme {
                GithubScreen(viewModel = viewModel)
            }
        }
    }
}
