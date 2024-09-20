package nextstep.github.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import nextstep.github.data.repository.GitHubRepository
import nextstep.github.data.repository.GitHubRepositoryImpl
import nextstep.github.data.service.GithubService
import nextstep.github.ui.usecase.GetGitHubRepositoryUseCase
import nextstep.github.ui.usecase.GetGitHubRepositoryUseCaseImpl
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

internal class AppContainer {
    private val serialization = Json { ignoreUnknownKeys = true }
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(serialization.asConverterFactory(MediaType.get(CONTENT_TYPE)))
        .build()

    private val gitHubService = retrofit.create(GithubService::class.java)

    private val gitHubRepository: GitHubRepository = GitHubRepositoryImpl(gitHubService)

    val getGitHubRepositoryUseCase: GetGitHubRepositoryUseCase =
        GetGitHubRepositoryUseCaseImpl(gitHubRepository)

    companion object {
        private const val CONTENT_TYPE = "application/json"
        private const val BASE_URL = "https://api.github.com/"
    }
}