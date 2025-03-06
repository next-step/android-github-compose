package nextstep.github.data

import nextstep.github.network.GithubService
import nextstep.github.network.RepositoryEntity

class GithubRepositoryImpl(private val githubService: GithubService) : GithubRepository {
    override suspend fun getRepositories(organization: String): List<RepositoryEntity> =
        githubService.getRepositories(organization = organization)
}