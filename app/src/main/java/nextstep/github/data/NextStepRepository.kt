package nextstep.github.data

import nextstep.github.model.NextStepRepositoryEntity
import nextstep.github.network.NextStepGithubService

class NextStepRepository(private val nextStepGithubService: NextStepGithubService) {
    suspend fun getRepositories(organization: String): List<NextStepRepositoryEntity> {
        return nextStepGithubService.getRepositories(organization)
    }
}
