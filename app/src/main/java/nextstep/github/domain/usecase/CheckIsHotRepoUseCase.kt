package nextstep.github.domain.usecase

import nextstep.github.data.repository.api.GithubRepository
import nextstep.github.domain.entity.Repository

class GetRepositoryListUseCase(
    private val githubRepository: GithubRepository,
) {

    suspend operator fun invoke(): List<Repository> {
        return githubRepository.getRepos().map {
            Repository(
                fullName = it.fullName.orEmpty(),
                description = it.description.orEmpty(),
                stars = it.stars ?: 0,
                isHot = (it.stars ?: 0) > 50,
            )
        }
    }
}