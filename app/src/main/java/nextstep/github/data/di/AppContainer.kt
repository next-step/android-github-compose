package nextstep.github.data.di

import nextstep.github.data.GithubNetworkModule.provideGithubDataSource
import nextstep.github.data.GithubNetworkModule.provideGithubRepository
import nextstep.github.data.GithubNetworkModule.provideGithubRetrofit
import nextstep.github.data.GithubNetworkModule.provideGithubService
import nextstep.github.data.GithubNetworkModule.provideJsonConverterFactory
import nextstep.github.data.datasource.api.GithubDataSource
import nextstep.github.data.repository.api.GithubRepository
import nextstep.github.data.service.GithubService
import retrofit2.Converter
import retrofit2.Retrofit

interface AppContainer {
    val jsonConverter: Converter.Factory
    val retrofit: Retrofit
    val githubService: GithubService
    val githubDataSource: GithubDataSource
    val githubRepository: GithubRepository
}

class AppContainerImpl : AppContainer {

    override val jsonConverter = provideJsonConverterFactory()
    override val retrofit = provideGithubRetrofit(jsonConverter)
    override val githubService = provideGithubService(retrofit)
    override val githubDataSource = provideGithubDataSource(githubService)
    override val githubRepository = provideGithubRepository(githubDataSource)
}