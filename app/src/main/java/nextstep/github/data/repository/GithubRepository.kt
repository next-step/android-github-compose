package nextstep.github.data.repository

import nextstep.github.data.model.RepositoryEntity

interface GithubRepository {

    suspend fun getRepositories(organization: String): List<RepositoryEntity>
}
