package nextstep.github.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import nextstep.github.model.GithubRepositoryDto
import nextstep.github.data.remote.api.GithubApiService

class GithubRemoteDataSourceImpl(
    private val githubApiService: GithubApiService,
) : GithubRemoteDataSource {
    override suspend fun getRepositories(organization: String): List<GithubRepositoryDto> {
        return withContext(Dispatchers.IO) {
            githubApiService.getRepositories(organization)
        }
    }
}
