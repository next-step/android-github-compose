package nextstep.github.data.repositories.impls

import nextstep.github.data.entities.RepositoryEntity
import nextstep.github.data.repositories.GithubRepository
import nextstep.github.data.services.GithubService

class RemoteGithubRepository(
    private val githubService: GithubService,
) : GithubRepository {
    override suspend fun getRepositories(organization: String): List<RepositoryEntity> {
        return githubService.getRepositories(organization = organization)
    }
}
