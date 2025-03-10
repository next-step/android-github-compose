package nextstep.github.data.services

import nextstep.github.model.RepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("orgs/{organization}/repos")
    suspend fun getRepositories(@Path("organization") organization: String): List<RepositoryResponse>
}
