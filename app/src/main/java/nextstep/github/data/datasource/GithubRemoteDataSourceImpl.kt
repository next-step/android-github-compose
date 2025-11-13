package nextstep.github.data.datasource

import nextstep.github.data.model.RepositoryEntity
import nextstep.github.data.service.GithubService

class GithubRemoteDataSourceImpl(
    private val service: GithubService,
): GithubRemoteDataSource {

    override suspend fun getRepositories(organization: String): List<RepositoryEntity> {
        return service.getRepositories(organization)
    }
}
