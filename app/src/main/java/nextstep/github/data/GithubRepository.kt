package nextstep.github.data

import nextstep.github.model.RepositoryEntity

interface GithubRepository {

    suspend fun getRepositories(organization: String): List<RepositoryEntity>
}
