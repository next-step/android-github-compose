package nextstep.github.data.repository.impl

import nextstep.github.data.repository.GithubRepository
import nextstep.github.data.service.GithubService
import nextstep.github.data.repository.model.RepositoryEntity

class GithubRepositoryImpl(private val service: GithubService) : GithubRepository {
    override suspend fun getRepositories(organization: String) : List<RepositoryEntity> {
        return service.getRepositories(organization)
    }
}
