package nextstep.github.data.repositories.impls

import nextstep.github.model.Repository
import nextstep.github.data.repositories.GithubRepository
import nextstep.github.data.services.GithubService

class RemoteGithubRepository(
    private val githubService: GithubService,
) : GithubRepository {
    override suspend fun getRepositories(organization: String): List<Repository> {
        return githubService.getRepositories(organization = organization)
    }
}
