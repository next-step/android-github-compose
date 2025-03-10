package nextstep.github.data.repository

import nextstep.github.data.datasource.GitHubRemoteDataSource
import nextstep.github.data.model.dto.RepositoryResponse

class GitHubRepositoryImpl(
    private val _remoteDataSource: GitHubRemoteDataSource
) : GitHubRepository {
    override suspend fun getRepositories(): List<RepositoryResponse> {
        return _remoteDataSource.getRepositories()
    }
}
