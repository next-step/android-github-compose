package nextstep.github.data.repository

import nextstep.github.data.model.GithubRepositoryData
import retrofit2.Response


interface GithubRepository {
    suspend fun getRepositories(organization: String): Response<List<GithubRepositoryData>>
}
