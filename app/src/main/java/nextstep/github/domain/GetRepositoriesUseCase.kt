package nextstep.github.domain

import nextstep.github.data.GithubRepository
import nextstep.github.model.GithubRepositoryDto

class GetRepositoriesUseCase(
    private val githubRepository: GithubRepository,
) {
    suspend operator fun invoke(organization: String): List<GithubRepositoryDto> {
        return githubRepository.getRepositories(organization)
    }
}
