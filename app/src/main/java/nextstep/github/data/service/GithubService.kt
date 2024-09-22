package nextstep.github.data.service

import nextstep.github.data.model.RemoteGitHubRepoInfo
import retrofit2.http.GET
import retrofit2.http.Path

internal interface GithubService {
    @GET("orgs/{organization}/repos")
    suspend fun getRepositories(
        @Path("organization") organization: String
    ): List<RemoteGitHubRepoInfo>
}