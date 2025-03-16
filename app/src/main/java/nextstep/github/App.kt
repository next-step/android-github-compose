package nextstep.github

import android.app.Application
import nextstep.github.di.AppContainer

class App : Application() {
    val appContainer = AppContainer()
}