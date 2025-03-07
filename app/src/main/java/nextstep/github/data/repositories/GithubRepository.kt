package nextstep.github.data.repositories

import nextstep.github.data.entities.RepositoryEntity

interface GithubRepository {

    suspend fun getRepositories(organization: String): List<RepositoryEntity>
}
