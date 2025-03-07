package nextstep.github.data.services

import nextstep.github.data.entities.RepositoryEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("orgs/{organization}/repos")
    suspend fun getRepositories(@Path("organization") organization: String): List<RepositoryEntity>
}
