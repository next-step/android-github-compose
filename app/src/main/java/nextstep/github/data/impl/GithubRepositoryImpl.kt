package nextstep.github.data.impl

import nextstep.github.data.GithubRepository
import nextstep.github.data.GithubService
import nextstep.github.data.RepositoryEntity

class GithubRepositoryImpl(
    private val githubService: GithubService
) : GithubRepository {
    override suspend fun getRepositories(): Result<List<RepositoryEntity>> {
        return try {
            val repositories = githubService.getRepositories("next-step")
            if (repositories.isEmpty()) {
                Result.failure(Exception("데이터 비어있음"))
            } else {
                Result.success(repositories)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
