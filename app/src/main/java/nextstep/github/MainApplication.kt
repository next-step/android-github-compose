package nextstep.github

import android.app.Application
import nextstep.github.di.AppContainer

class MainApplication : Application() {
    val appContainer = AppContainer()
}