import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import nextstep.github.data.GithubRepository
import nextstep.github.data.GithubService
import nextstep.github.data.impl.GithubRepositoryImpl
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

    val githubRepository: GithubRepository = GithubRepositoryImpl(githubService) // githubService 인스턴스를 활용하여 초기화

    companion object {
        private const val CONTENT_TYPE = "application/json"
        private const val BASE_URL = "https://api.github.com"
    }
}
