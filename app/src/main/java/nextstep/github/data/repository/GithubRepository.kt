package nextstep.github.data.repository

import nextstep.github.data.repository.model.RepositoryEntity

interface GithubRepository {
    suspend fun getRepositories(organization: String) : List<RepositoryEntity>
}
