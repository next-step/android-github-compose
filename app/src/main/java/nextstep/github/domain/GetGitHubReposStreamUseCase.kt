package nextstep.github.domain

import kotlinx.coroutines.flow.Flow
import nextstep.github.data.repositories.GithubRepoRepository
import nextstep.github.model.GitHubRepo

class GetGitHubReposStreamUseCase(
    private val githubRepoRepository: GithubRepoRepository,
) {
    operator fun invoke(organization: String): Flow<List<GitHubRepo>> {
        return githubRepoRepository.getGitHubReposStream(organization)
    }
}
