package nextstep.github.core.network

import ResponseRepositoriesItem
import nextstep.github.core.network.service.GithubService

class GithubApiClient(
    private val githubService: GithubService,
) : ApiClient {
    override suspend fun getRepositories(organization: String): Result<List<ResponseRepositoriesItem>> =
        runCatching { githubService.getRepositories(organization) }
}
