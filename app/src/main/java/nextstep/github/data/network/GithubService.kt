package nextstep.github.data.network

import nextstep.github.data.model.GithubRepositoryData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface GithubService {
    @GET("orgs/{organization}/repos")
    suspend fun getRepositories(@Path("organization") organization: String): Response<List<GithubRepositoryData>>
}
