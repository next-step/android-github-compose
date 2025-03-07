package nextstep.github.data

import nextstep.github.network.Result

class GithubRepository(
    private val githubService: GithubService
) {
    private val remoteDataSource: GithubRemoteDataSource = GithubRemoteDataSource(githubService)
    private val localDataSource: GithubLocalDataSource = GithubLocalDataSource()

    suspend fun getRepositories(organization: String): Result<List<RepositoryEntity>> {
        return remoteDataSource.fetchRepositories(organization)
    }
}