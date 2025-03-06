package nextstep.github.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Converter
import retrofit2.Retrofit

object GithubNetworkModule {

    const val GITHUB_BASE_URL = "https://api.github.com/"
    private const val CONTENT_TYPE = "application/json"

    fun provideGithubRetrofit(
        jsonConverterFactory: Converter.Factory,
    ) = Retrofit.Builder()
        .baseUrl(GITHUB_BASE_URL)
        .addConverterFactory(jsonConverterFactory)
        .build()

    fun provideJsonConverterFactory(): Converter.Factory = Json { ignoreUnknownKeys = true }
        .asConverterFactory(CONTENT_TYPE.toMediaType())
}