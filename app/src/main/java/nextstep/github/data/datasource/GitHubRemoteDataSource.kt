package nextstep.github.data.datasource

import nextstep.github.data.model.dto.RepositoryResponse

interface GitHubRemoteDataSource {
    suspend fun getRepositories(): List<RepositoryResponse>
}
