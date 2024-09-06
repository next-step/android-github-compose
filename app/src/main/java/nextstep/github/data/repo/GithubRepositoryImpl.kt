package nextstep.github.data.repo

import nextstep.github.data.model.GithubReposResponse
import nextstep.github.data.network.GithubService

class GithubRepositoryImpl(
    private val githubService: GithubService
) : GithubRepository {
    override suspend fun fetchRepos(organization: String): List<GithubReposResponse> =
        githubService.getRepositories(organization)

}
