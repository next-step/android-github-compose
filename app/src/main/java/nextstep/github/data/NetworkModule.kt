package nextstep.github.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import nextstep.github.data.datasource.api.GithubDataSource
import nextstep.github.data.datasource.impl.GithubDataSourceImpl
import nextstep.github.data.repository.api.GithubRepository
import nextstep.github.data.repository.impl.GithubRepositoryImpl
import nextstep.github.data.service.GithubService
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Converter
import retrofit2.Retrofit

object GithubNetworkModule {

    const val GITHUB_BASE_URL = "https://api.github.com/"
    private const val CONTENT_TYPE = "application/json"

    fun provideGithubRetrofit(
        jsonConverterFactory: Converter.Factory,
    ) = Retrofit.Builder()
        .baseUrl(GITHUB_BASE_URL)
        .addConverterFactory(jsonConverterFactory)
        .build()

    fun provideJsonConverterFactory(): Converter.Factory = Json { ignoreUnknownKeys = true }
        .asConverterFactory(CONTENT_TYPE.toMediaType())

    fun provideGithubService(retrofit: Retrofit): GithubService =
        retrofit.create(GithubService::class.java)

    fun provideGithubDataSource(githubService: GithubService): GithubDataSource =
        GithubDataSourceImpl(githubService)

    fun provideGithubRepository(githubDataSource: GithubDataSource): GithubRepository =
        GithubRepositoryImpl(githubDataSource)
}