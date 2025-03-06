package nextstep.github.data.service

import nextstep.github.data.model.RepositoryResponseModel
import retrofit2.http.GET

interface GithubService {

    @GET("orgs/next-step/repos")
    suspend fun getRepos(): List<RepositoryResponseModel>
}