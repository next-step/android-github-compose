package nextstep.github.core.network

import ResponseRepositoriesItem

interface ApiClient {
    suspend fun getRepositories(organization: String): Result<List<ResponseRepositoriesItem>>
}
