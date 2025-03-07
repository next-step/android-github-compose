package nextstep.github.data

import nextstep.github.network.Result
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("orgs/{organization}/repos")
    suspend fun getRepositories(@Path("organization") organization: String): Result<List<RepositoryEntity>>
}