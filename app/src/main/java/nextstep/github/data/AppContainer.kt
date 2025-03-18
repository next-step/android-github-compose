package nextstep.github.data

import android.util.Log
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import nextstep.github.data.repositories.GithubRepoRepository
import nextstep.github.data.repositories.impls.RemoteGithubRepoRepository
import nextstep.github.data.services.GithubRepoService
import nextstep.github.domain.GetGitHubReposStreamUseCase
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class AppContainer {
    private val serialization = Json { ignoreUnknownKeys = true }

    private val httpLoggingInterceptor = HttpLoggingInterceptor { message ->
        Log.d(TAG_HTTP_LOG, message)
    }.apply { setLevel(HttpLoggingInterceptor.Level.BODY) }

    private val okHttpClient = OkHttpClient
        .Builder()
        .addInterceptor(httpLoggingInterceptor)
        .connectTimeout(CONNECT_TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(WRITE_TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(READ_TIMEOUT, TimeUnit.SECONDS)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(serialization.asConverterFactory(CONTENT_TYPE.toMediaType()))
        .build()

    private val githubRepoService = retrofit.create(GithubRepoService::class.java)
    private val githubRepoRepository: GithubRepoRepository = RemoteGithubRepoRepository(githubRepoService)
    val getGitHubReposStreamUseCase = GetGitHubReposStreamUseCase(githubRepoRepository)

    companion object {
        private const val TAG_HTTP_LOG = "Http_Log"
        private const val CONNECT_TIMEOUT = 20L
        private const val WRITE_TIMEOUT = 20L
        private const val READ_TIMEOUT = 20L
        private const val CONTENT_TYPE = "application/json"
        private const val BASE_URL = "https://api.github.com"
    }
}
