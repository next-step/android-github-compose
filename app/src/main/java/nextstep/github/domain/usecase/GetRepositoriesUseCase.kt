package nextstep.github.domain.usecase

import nextstep.github.data.repository.GithubRepoRepository
import nextstep.github.domain.model.Repository
import nextstep.github.domain.model.toDomain

fun interface GetRepositoriesUseCase {
    suspend operator fun invoke(organization: String): List<Repository>
}

class DefaultGetRepositoriesUseCase(
    private val githubRepoRepository: GithubRepoRepository,
) : GetRepositoriesUseCase {
    override suspend operator fun invoke(organization: String): List<Repository> {
        return githubRepoRepository.getRepositories(organization).map { it.toDomain() }
    }
}
