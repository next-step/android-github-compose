package nextstep.github.network

import nextstep.github.model.NextStepRepositoryEntity
import retrofit2.http.GET
import retrofit2.http.Path

interface NextStepGithubService {
    @GET("orgs/{organization}/repos")
    suspend fun getRepositories(@Path("organization") organization: String): List<NextStepRepositoryEntity>
}
