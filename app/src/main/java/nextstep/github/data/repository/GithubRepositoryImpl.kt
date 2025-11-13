package nextstep.github.data.repository

import nextstep.github.data.datasource.GithubRemoteDataSource
import nextstep.github.data.model.RepositoryEntity

class GithubRepositoryImpl(
    private val remoteDataSource: GithubRemoteDataSource,
): GithubRepository {

    override suspend fun getRepositories(organization: String): List<RepositoryEntity> {
        return remoteDataSource.getRepositories(organization)
    }
}
