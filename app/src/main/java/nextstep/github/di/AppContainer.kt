package nextstep.github.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import nextstep.github.data.remote.api.GithubService
import nextstep.github.data.repository.GithubRepository
import nextstep.github.data.repository.GithubRepositoryImpl
import nextstep.github.domain.GithubRepositoryUseCase
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class AppContainer {
    private val serialization = Json { ignoreUnknownKeys = true }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient
                .Builder()
                .addInterceptor(HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                })
                .build()
        )
        .addConverterFactory(serialization.asConverterFactory(contentType = CONTENT_TYPE.toMediaType()))
        .build()

    private val githubService = retrofit
        .create(GithubService::class.java)

    val githubRepository: GithubRepository by lazy { GithubRepositoryImpl(githubService) }

    val githubRepositoryUseCase: GithubRepositoryUseCase by lazy { GithubRepositoryUseCase(githubRepository) }

    companion object {
        private const val CONTENT_TYPE = "application/json"
        private const val BASE_URL = "https://api.github.com"
    }
}
