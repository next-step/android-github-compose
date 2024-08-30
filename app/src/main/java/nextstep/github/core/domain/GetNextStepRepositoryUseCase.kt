package nextstep.github.core.domain

import nextstep.github.core.data.GithubRepository
import nextstep.github.core.model.NextStepRepository

class GetNextStepRepositoryUseCase(
    private val githubRepository: GithubRepository,
) {
    suspend operator fun invoke(): Result<List<NextStepRepository>> = TODO("Not yet implemented")
}
