package nextstep.github.core.data

import nextstep.github.core.model.Organization
import nextstep.github.core.model.RepositoryEntity

interface GithubRepository {
    suspend fun getRepositories(organization: Organization): Result<List<RepositoryEntity>>
}
