package nextstep.github.core.network

import nextstep.github.core.model.RepositoryEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface GithubService {
    @GET("orgs/{organization}/repos")
    suspend fun getRepositories(@Path("organization") organization: String): Response<List<RepositoryEntity>>
}
