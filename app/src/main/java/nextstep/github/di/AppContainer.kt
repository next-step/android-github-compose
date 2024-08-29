package nextstep.github.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import nextstep.github.data.repository.GithubRepository
import nextstep.github.data.repository.impl.GithubRepositoryImpl
import nextstep.github.data.service.GithubService
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class AppContainer {

    private val serialization = Json {
        ignoreUnknownKeys = true
        prettyPrint = true
    }

    private val provideOkHttpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY

            }
        )
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(provideOkHttpClient)
        .addConverterFactory(serialization.asConverterFactory(CONTENT_TYPE.toMediaType()))
        .build()

    private val githubService = retrofit.create(GithubService::class.java)

    val githubRepository: GithubRepository = GithubRepositoryImpl(githubService)

    companion object {
        private const val CONTENT_TYPE = "application/json"
        private const val BASE_URL = "https://api.github.com"
    }
}
