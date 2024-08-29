package nextstep.github.core.data

import nextstep.github.core.model.RepositoryEntity

interface GithubRepository {
    suspend fun getRepositories(organization: String): Result<List<RepositoryEntity>>
}
