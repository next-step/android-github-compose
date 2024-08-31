package nextstep.github.domain

interface RepositoryUseCase {
    suspend fun getRepositories(): Result<List<Repository>>
}