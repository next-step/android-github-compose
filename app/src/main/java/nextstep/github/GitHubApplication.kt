package nextstep.github

import android.app.Application
import nextstep.github.di.AppContainer

class GitHubApplication : Application() {
    val appContainer = AppContainer()
}
