package nextstep.github.data.remote

import nextstep.github.data.remote.api.GithubService
import nextstep.github.model.RepositoryEntity

class GithubRemoteDataSourceImpl(
    private val githubService: GithubService,
) : GithubRemoteDataSource {

    override suspend fun getRepositories(organization: String): Result<List<RepositoryEntity>> {
        return try {
            Result.success(githubService.getRepositories(organization))
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
