package nextstep.github

import android.app.Application
import nextstep.github.data.di.AppContainer

class GithubApplication : Application() {

    val appContainer = AppContainer()
}
