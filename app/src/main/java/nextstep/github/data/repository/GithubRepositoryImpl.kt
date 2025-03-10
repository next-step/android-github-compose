package nextstep.github.data.repository

import nextstep.github.data.api.GithubService
import nextstep.github.data.mapper.toRepository
import nextstep.github.model.Repository

class GithubRepositoryImpl(private val githubService: GithubService) : GithubRepository {
    override suspend fun getRepositories(organization: String): List<Repository> =
        githubService.getRepositories(organization = organization).map { it.toRepository() }
}