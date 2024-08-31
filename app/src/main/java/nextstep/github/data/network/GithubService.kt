package nextstep.github.data.network

import nextstep.github.data.model.GithubReposResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("orgs/{organization}/repos")
    suspend fun getRepositories(@Path("organization") organization: String): List<GithubReposResponse>
}
