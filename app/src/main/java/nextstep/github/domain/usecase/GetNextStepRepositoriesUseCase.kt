package nextstep.github.domain.usecase

import nextstep.github.data.repository.GitHubRepository
import nextstep.github.domain.model.Repository

class GetNextStepRepositoriesUseCase(
    private val repository: GitHubRepository
) {
    suspend operator fun invoke(): Result<List<Repository>> =
        repository.getNextStepRepositories().mapCatching { it.map(Repository::fromResponse) }
}
