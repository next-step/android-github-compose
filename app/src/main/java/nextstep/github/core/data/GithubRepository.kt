package nextstep.github.core.data

import nextstep.github.core.model.RepositoryEntity
import nextstep.github.core.network.ApiResult


interface GithubRepository {
    suspend fun getRepositories(organization: String): ApiResult<List<RepositoryEntity>>
}
