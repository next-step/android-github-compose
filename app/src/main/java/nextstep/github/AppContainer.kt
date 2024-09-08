package nextstep.github

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import nextstep.github.data.NextStepRepository
import nextstep.github.network.NextStepGithubService
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

class AppContainer {
    private val serialization = Json { ignoreUnknownKeys = true }
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(
            serialization.asConverterFactory(
                MediaType.get(CONTENT_TYPE)
            )
        )
        .build()

    private val nextStepGithubService = retrofit
        .create(NextStepGithubService::class.java)

    val nextStepRepository = NextStepRepository(nextStepGithubService)

    companion object {
        private const val CONTENT_TYPE = "application/json"
        private const val BASE_URL = "https://api.github.com"
    }
}

