package nextstep.github.data

import nextstep.github.model.NextStepRepositoryEntity
import nextstep.github.network.NextStepGithubService

class NextStepRepository(private val nextStepGithubService: NextStepGithubService) :
    NextStepGithubService {
    override suspend fun getRepositories(organization: String): List<NextStepRepositoryEntity> {
        return nextStepGithubService.getRepositories(organization)
    }
}
