package nextstep.github.data.repository

import nextstep.github.data.datasource.GitHubRemoteDataSource
import nextstep.github.data.model.dto.RepositoryResponse

class GitHubRepositoryImpl(
    private val remoteDataSource: GitHubRemoteDataSource
) : GitHubRepository {
    override suspend fun getNextStepRepositories(): List<RepositoryResponse> {
        return remoteDataSource.getNextStepRepositories()
    }
}
