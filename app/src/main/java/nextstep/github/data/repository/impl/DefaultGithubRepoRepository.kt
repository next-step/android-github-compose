package nextstep.github.data.repository.impl

import nextstep.github.data.repository.GithubRepoRepository
import nextstep.github.data.service.GithubService
import nextstep.github.data.repository.model.RepositoryEntity

class DefaultGithubRepoRepository(private val service: GithubService) : GithubRepoRepository {
    override suspend fun getRepositories(organization: String) : List<RepositoryEntity> {
        return service.getRepositories(organization)
    }
}
