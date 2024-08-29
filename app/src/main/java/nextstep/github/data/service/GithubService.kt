package nextstep.github.data.service

import nextstep.github.data.response.RepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("orgs/{organization}/repos")
    suspend fun getRepositories(
        @Path("organization") organization: String
    ): List<RepositoryResponse>
}
