package nextstep.github.core.domain

import nextstep.github.core.data.GithubRepository
import nextstep.github.core.model.NextStepRepository
import nextstep.github.core.model.Organization

class GetNextStepRepositoryUseCase(
    private val githubRepository: GithubRepository,
) {
    suspend operator fun invoke(): Result<List<NextStepRepository>> =
        githubRepository
            .getRepositories(Organization.NEXT_STEP)
            .map {
                it.map { item ->
                    if (item.stars >= NextStepRepository.HOT_STARS) {
                        NextStepRepository.Hot(item.fullName, item.description, item.stars)
                    } else {
                        NextStepRepository.Normal(item.fullName, item.description, item.stars)
                    }
                }
            }
}
