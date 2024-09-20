package nextstep.github.data.repository

import nextstep.github.data.model.RemoteGitHubRepoInfo
import nextstep.github.data.service.GithubService

internal interface GitHubRepository {
    suspend fun getRepositories(organization: String): List<RemoteGitHubRepoInfo>
}

internal class GitHubRepositoryImpl(
    private val githubService: GithubService
) : GitHubRepository {
    override suspend fun getRepositories(organization: String): List<RemoteGitHubRepoInfo> {
        return githubService.getRepositories(organization)
    }
}