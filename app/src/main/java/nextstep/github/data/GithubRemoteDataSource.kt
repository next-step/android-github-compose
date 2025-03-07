package nextstep.github.data

import nextstep.github.network.Result

class GithubRemoteDataSource(
    private val githubService: GithubService
) {
     suspend fun fetchRepositories(organization: String): Result<List<RepositoryEntity>> {
        return githubService.getRepositories(organization)
    }
}