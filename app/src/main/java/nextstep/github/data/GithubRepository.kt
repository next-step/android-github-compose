package nextstep.github.data

import nextstep.github.network.RepositoryEntity

interface GithubRepository {

    suspend fun getRepositories(organization: String): List<RepositoryEntity>
}