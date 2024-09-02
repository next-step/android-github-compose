package nextstep.github.core.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import nextstep.github.core.data.GithubRepository
import nextstep.github.core.data.GithubRepositoryImpl
import nextstep.github.core.network.GithubService
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class AppContainer {

    private val serialization = Json { ignoreUnknownKeys = true }
    private val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(
            OkHttpClient
                .Builder()
                .addInterceptor(httpLoggingInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build()
        )
        .addConverterFactory(serialization.asConverterFactory(CONTENT_TYPE.toMediaType()))
        .build()

    private val githubService = retrofit
        .create(GithubService::class.java)

    val githubRepository: GithubRepository = GithubRepositoryImpl(githubService)

    companion object {
        private const val CONTENT_TYPE = "application/json"
        private const val BASE_URL = "https://api.github.com"
    }
}
