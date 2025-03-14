package nextstep.github.data.datasource

import nextstep.github.data.api.GitHubApiService
import nextstep.github.data.model.dto.RepositoryResponse

class GithubRemoteDataSourceImpl(
    private val service: GitHubApiService,
) : GitHubRemoteDataSource {
    override suspend fun getNextStepRepositories(): List<RepositoryResponse> {
        return service.getNextStepRepositories()
    }
}
