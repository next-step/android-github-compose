package nextstep.github.data

import nextstep.github.data.remote.GithubRemoteDataSource
import nextstep.github.model.RepositoryEntity

class GithubRepositoryImpl(
    private val githubRemoteDataSource: GithubRemoteDataSource,
) : GithubRepository {

    override suspend fun getRepositories(organization: String): Result<List<RepositoryEntity>> =
        githubRemoteDataSource.getRepositories(organization)

}
