package nextstep.github

import android.app.Application
import nextstep.github.core.di.AppContainer

class MainApplication : Application() {
    val appContainer by lazy { AppContainer() }

    override fun onCreate() {
        super.onCreate()
    }
}
