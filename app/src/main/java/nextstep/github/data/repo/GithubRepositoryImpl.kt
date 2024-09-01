package nextstep.github.data.repo

import nextstep.github.data.model.GithubReposResponse
import nextstep.github.data.network.GithubService

class GithubRepositoryImpl(
    private val githubService: GithubService
) : GithubRepository {
    override suspend fun fetchRepos(organization: String): Result<List<GithubReposResponse>> {
        return try {
            val result = githubService.getRepositories(organization)
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(Exception("$organization Repositories not found"))
        }
    }

}
