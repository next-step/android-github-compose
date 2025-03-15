package nextstep.github.data.services

import nextstep.github.data.responses.GitHubRepoResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubRepoService {
    @GET("orgs/{organization}/repos")
    suspend fun getGitHubRepos(@Path("organization") organization: String): List<GitHubRepoResponse>
}
