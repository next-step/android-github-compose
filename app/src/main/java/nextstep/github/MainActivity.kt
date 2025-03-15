package nextstep.github

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import nextstep.github.ui.nextsteprepos.NextStepReposViewModel
import nextstep.github.ui.nextsteprepos.NextStepReposScreen

class MainActivity : ComponentActivity() {

    private val githubViewModel: NextStepReposViewModel by viewModels { NextStepReposViewModel.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NextStepReposScreen(
                viewModel = githubViewModel
            )
        }
    }
}

