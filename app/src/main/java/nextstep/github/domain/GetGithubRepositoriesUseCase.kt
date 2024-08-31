package nextstep.github.domain

interface GetGithubRepositoriesUseCase {
    suspend fun getRepositories(): Result<List<Repository>>
}