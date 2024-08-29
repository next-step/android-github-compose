package nextstep.github.core.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import nextstep.github.core.model.RepositoryEntity
import nextstep.github.core.network.ApiClient
import toEntity

class GithubRepositoryImpl(
    private val apiClient: ApiClient,
) : GithubRepository {
    override suspend fun getRepositories(organization: String): Result<List<RepositoryEntity>> =
        withContext(Dispatchers.IO) {
            apiClient.getRepositories(organization).map {
                it.map { response -> response.toEntity() }
            }
        }
}
