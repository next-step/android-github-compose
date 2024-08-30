package nextstep.github

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import nextstep.github.data.network.GithubService
import nextstep.github.data.repo.GithubRepository
import nextstep.github.data.repo.GithubRepositoryImpl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class AppContainer {

    private val serialization = Json { ignoreUnknownKeys = true }
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(serialization.asConverterFactory(CONTENT_TYPE.toMediaType()))
        .build()

    private val githubService = retrofit
        .create(GithubService::class.java)

    val githubRepository: GithubRepository = GithubRepositoryImpl(githubService = githubService)


    companion object {
        private const val CONTENT_TYPE = "application/json"
        private const val BASE_URL = "https://api.github.com"
    }
}
