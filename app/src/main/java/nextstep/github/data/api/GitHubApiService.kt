package nextstep.github.data.api

import nextstep.github.data.model.dto.RepositoryResponse
import retrofit2.http.GET

interface GitHubApiService {

    @GET("orgs/next-step/repos")
    suspend fun getRepositories(): List<RepositoryResponse>
}
