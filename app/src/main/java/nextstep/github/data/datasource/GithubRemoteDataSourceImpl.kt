package nextstep.github.data.datasource

import nextstep.github.data.api.GitHubApiService
import nextstep.github.data.model.dto.RepositoryResponse

class GithubRemoteDataSourceImpl(
    private val _service: GitHubApiService,
) : GitHubRemoteDataSource {
    override suspend fun getRepositories(): List<RepositoryResponse> {
        return _service.getRepositories()
    }
}
