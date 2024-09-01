package nextstep.github.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import nextstep.github.data.repository.GithubRepositoryImpl
import nextstep.github.data.repository.GithubRepository
import nextstep.github.data.service.GithubService
import nextstep.github.data.source.GithubDataSource
import nextstep.github.data.source.GithubDataSourceImpl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class AppContainer {
    private val serialization = Json { ignoreUnknownKeys = true }
    private val retrofit = Retrofit
        .Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient
                .Builder()
                .addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    },
                ).build(),
        ).addConverterFactory(serialization.asConverterFactory((CONTENT_TYPE.toMediaType())))
        .build()

    private val githubApiService: GithubService = retrofit.create(GithubService::class.java)
    private val githubDataSource: GithubDataSource = GithubDataSourceImpl(githubApiService)
    val githubRepository: GithubRepository = GithubRepositoryImpl(githubDataSource)

    companion object {
        private const val CONTENT_TYPE = "application/json"
        private const val BASE_URL = "https://api.github.com"
    }
}
