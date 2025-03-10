package nextstep.github.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import nextstep.github.data.api.GitHubApiService
import nextstep.github.data.datasource.GitHubRemoteDataSource
import nextstep.github.data.datasource.GithubRemoteDataSourceImpl
import nextstep.github.data.repository.GitHubRepository
import nextstep.github.data.repository.GitHubRepositoryImpl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create

class AppContainer {

    private val serialization: Json = Json { ignoreUnknownKeys = true }

    private val client: OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(serialization.asConverterFactory(CONTENT_TYPE.toMediaType()))
        .build()

    private val githubService: GitHubApiService = retrofit.create<GitHubApiService>()
    private val githubRemoteSource: GitHubRemoteDataSource = GithubRemoteDataSourceImpl(githubService)
    val githubRepository: GitHubRepository = GitHubRepositoryImpl(githubRemoteSource)

    companion object {
        private const val CONTENT_TYPE = "application/json"
        private const val BASE_URL = "https://api.github.com/"
    }
}
