package nextstep.github.data.repository

import nextstep.github.data.entity.RepositoryEntity
import nextstep.github.data.service.GithubService

class GithubRepository(private val githubService: GithubService) {
    suspend fun getRepositories(organization: String): List<RepositoryEntity> {
        return githubService.getRepositories(organization)
    }
}