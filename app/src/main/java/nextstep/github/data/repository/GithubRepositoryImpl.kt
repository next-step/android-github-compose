package nextstep.github.data.repository

import nextstep.github.data.api.GithubService
import nextstep.github.data.api.model.RepositoryEntity

class GithubRepositoryImpl(private val githubService: GithubService) : GithubRepository {
    override suspend fun getRepositories(organization: String): List<RepositoryEntity> =
        githubService.getRepositories(organization = organization)
}