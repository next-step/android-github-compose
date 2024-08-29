package nextstep.github

import android.app.Application
import android.content.Context
import nextstep.github.di.AppContainer

class GithubApplication : Application() {
    val appContainer by lazy { AppContainer() }
}

fun Application.getAppContainer(): AppContainer {
    return (this as GithubApplication).appContainer
}