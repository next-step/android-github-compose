package nextstep.github.data.repository

import nextstep.github.data.model.response.RepositoryResponse
import nextstep.github.ui.model.RepositoryModel

interface GithubRepository {
    suspend fun getRepositories(organization: String): List<RepositoryResponse>
}
