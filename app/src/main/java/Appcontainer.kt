import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import nextstep.github.data.ExampleRepository
import nextstep.github.data.GithubService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import okhttp3.MediaType.Companion.toMediaType

class AppContainer {

    private val serialization = Json { ignoreUnknownKeys = true }
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(OkHttpClient.Builder().build())
        .addConverterFactory(serialization.asConverterFactory(CONTENT_TYPE.toMediaType()))
        .build()
    val githubService = retrofit
        .create(GithubService::class.java)

    val exampleRepository: ExampleRepository = ExampleRepository(githubService) // githubService 인스턴스를 활용하여 초기화

    companion object {
        private const val CONTENT_TYPE = "application/json"
        private const val BASE_URL = "https://api.github.com"
    }

}