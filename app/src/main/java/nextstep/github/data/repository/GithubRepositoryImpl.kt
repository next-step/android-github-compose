package nextstep.github.data.repository

import nextstep.github.data.model.response.RepositoryResponse
import nextstep.github.data.service.GithubService

class GithubRepositoryImpl(
    private val githubService: GithubService
) : GithubRepository {
    override suspend fun getRepositories(organization: String): List<RepositoryResponse> {
        return githubService.getRepositories(organization)
    }
}
