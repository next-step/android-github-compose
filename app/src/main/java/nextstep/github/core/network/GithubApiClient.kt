package nextstep.github.core.network

import ResponseRepositoriesDto
import nextstep.github.core.network.service.GithubService

class GithubApiClient(
    private val githubService: GithubService,
) : ApiClient {
    override suspend fun getRepositories(organization: String): Result<List<ResponseRepositoriesDto>> =
        runCatching { githubService.getRepositories(organization) }
}
