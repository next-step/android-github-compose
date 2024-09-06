package nextstep.github.data.repository.impl

import nextstep.github.data.repository.GithubRepository
import nextstep.github.data.response.RepositoryResponse
import nextstep.github.data.service.GithubService

internal class GithubRepositoryImpl(
    private val githubService: GithubService
) : GithubRepository {

    override suspend fun loadNextStepRepositories(): Result<List<RepositoryResponse>> {
        return runCatching {
            githubService.getRepositories("next-step")
        }
    }
}
