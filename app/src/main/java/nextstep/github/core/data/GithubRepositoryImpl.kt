package nextstep.github.core.data

import nextstep.github.core.model.RepositoryEntity
import nextstep.github.core.network.ApiResult
import nextstep.github.core.network.GithubService

class GithubRepositoryImpl(
    private val githubService: GithubService
) : GithubRepository {

    override suspend fun getRepositories(organization: String): ApiResult<List<RepositoryEntity>> {
        val response = githubService.getRepositories(organization)

        return if (response.isSuccessful && response.body() != null) {
            ApiResult.Success(response.body()!!)
        } else {
            ApiResult.Error(response.code(), Throwable(response.message()))
        }
    }
}
