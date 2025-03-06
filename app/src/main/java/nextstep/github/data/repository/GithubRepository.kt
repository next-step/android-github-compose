package nextstep.github.data.repository

import nextstep.github.data.api.model.RepositoryEntity

interface GithubRepository {

    suspend fun getRepositories(organization: String): List<RepositoryEntity>
}