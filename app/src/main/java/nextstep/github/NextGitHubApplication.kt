package nextstep.github

import android.app.Application
import nextstep.github.di.AppContainer

class NextGitHubApplication: Application() {

    val appContainer = AppContainer()
}