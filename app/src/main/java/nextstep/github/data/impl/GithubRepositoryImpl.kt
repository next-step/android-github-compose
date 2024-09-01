package nextstep.github.data.impl

import nextstep.github.data.GithubRepository
import nextstep.github.data.GithubService
import nextstep.github.data.RepositoryEntity

internal class GithubRepositoryImpl(
    private val githubService: GithubService
) : GithubRepository {
    override suspend fun getRepositories(): Result<List<RepositoryEntity>> {
        return try {
            val repositories = githubService.getRepositories("next-step")
            Result.success(repositories)
        } catch (e: Exception) {
            Result.failure(Exception("예상치 못한 오류가 발생했습니다."))
        }
    }
}
