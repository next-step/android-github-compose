package nextstep.github.data.repository

import nextstep.github.data.model.dto.RepositoryResponse

interface GitHubRepository {
    suspend fun getNextStepRepositories(): Result<List<RepositoryResponse>>
}
