package nextstep.github.core.network.service

import ResponseRepositoriesDto
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("orgs/{organization}/repos")
    suspend fun getRepositories(
        @Path("organization") organization: String,
    ): List<ResponseRepositoriesDto>
}
