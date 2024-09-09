package nextstep.github.core.data

import nextstep.github.core.model.GithubRepositoryData
import nextstep.github.core.network.GithubService
import retrofit2.Response

class GithubRepositoryImpl(
    private val githubService: GithubService
) : GithubRepository {

    override suspend fun getRepositories(organization: String): Response<List<GithubRepositoryData>> {
        return githubService.getRepositories(organization)
    }
}
