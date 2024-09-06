package nextstep.github.data.repository

import nextstep.github.data.response.RepositoryResponse

interface GithubRepository {

    suspend fun loadNextStepRepositories(): Result<List<RepositoryResponse>>
}
