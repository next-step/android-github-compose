package nextstep.github.domain.impl

import nextstep.github.data.GithubRepository
import nextstep.github.domain.Repository
import nextstep.github.domain.RepositoryUseCase

class RepositoryUseCaseImpl(
    private val repository: GithubRepository
) : RepositoryUseCase {
    override suspend fun getRepositories(): Result<List<Repository>> {
        return repository.getRepositories().map { entities ->
            entities.map { entity ->
                Repository(
                    fullName = entity.fullName ?: "",
                    description = entity.description ?: "",
                    star = entity.stars ?: 0
                )
            }
        }
    }
}