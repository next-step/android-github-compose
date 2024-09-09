package nextstep.github.data.repository

import nextstep.github.data.model.GithubRepositoryData
import nextstep.github.data.network.GithubService
import retrofit2.Response

class GithubRepositoryImpl(
    private val githubService: GithubService
) : GithubRepository {

    override suspend fun getRepositories(organization: String): Response<List<GithubRepositoryData>> {
        return githubService.getRepositories(organization)
    }
}
