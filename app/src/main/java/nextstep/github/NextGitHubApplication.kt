package nextstep.github

import android.app.Application
import nextstep.github.data.di.AppContainer
import nextstep.github.data.di.AppContainerImpl

class NextGitHubApplication : Application() {

    val appContainer: AppContainer = AppContainerImpl()
}