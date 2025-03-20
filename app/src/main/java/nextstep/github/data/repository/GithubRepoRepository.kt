package nextstep.github.data.repository

import nextstep.github.data.api.GithubService
import nextstep.github.data.dto.toGithubRepo
import nextstep.github.model.GithubRepo

class GithubRepoRepository(
    private val githubService: GithubService
) {
    suspend fun getRepos(
        organization: String,
    ): Result<List<GithubRepo>> = runCatching {
        githubService.getRepositories(organization = organization)
            .map { dto -> dto.toGithubRepo() }
    }
}
