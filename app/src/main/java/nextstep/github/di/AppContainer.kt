package nextstep.github.di

import nextstep.github.data.GithubNetworkModule.provideGithubRetrofit
import nextstep.github.data.GithubNetworkModule.provideJsonConverterFactory
import retrofit2.Converter
import retrofit2.Retrofit

interface AppContainer {
    val jsonConverter: Converter.Factory
    val retrofit: Retrofit
}

class AppContainerImpl : AppContainer {

    override val jsonConverter = provideJsonConverterFactory()
    override val retrofit = provideGithubRetrofit(jsonConverter)
}