package nextstep.github.remote.api

import nextstep.github.model.GithubRepositoryDto
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApiService {
    @GET("orgs/{organization}/repos")
    suspend fun getRepositories(@Path("organization") organization: String): List<GithubRepositoryDto>
}
