package nextstep.github.core.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import nextstep.github.core.data.GithubRepository
import nextstep.github.core.data.GithubRepositoryImpl
import nextstep.github.core.domain.GeOrganizationRepositoryUseCase
import nextstep.github.core.network.ApiClient
import nextstep.github.core.network.GithubApiClient
import nextstep.github.core.network.service.GithubService
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class AppContainer {
    private val serialization = Json { ignoreUnknownKeys = true }
    private val retrofit =
        Retrofit
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

    private val githubService =
        retrofit
            .create(GithubService::class.java)

    private val apiClient: ApiClient = GithubApiClient(githubService)

    val githubRepository: GithubRepository =
        GithubRepositoryImpl(apiClient)

    val geOrganizationRepositoryUseCase = GeOrganizationRepositoryUseCase(githubRepository)

    companion object {
        private const val CONTENT_TYPE = "application/json"
        private const val BASE_URL = "https://api.github.com/"
    }
}
