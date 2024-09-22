package nextstep.github

import android.app.Application
import nextstep.github.di.AppContainer

internal class NextStepApp : Application() {
    val appContainer = AppContainer()
}