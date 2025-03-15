package nextstep.github.data.api

import nextstep.github.data.dto.RepositoryResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("orgs/{organization}/repos")
    suspend fun getRepositories(
        @Path("organization") organization: String
    ): List<RepositoryResponse>
}
