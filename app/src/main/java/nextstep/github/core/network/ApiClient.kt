package nextstep.github.core.network

import ResponseRepositoriesDto

interface ApiClient {
    suspend fun getRepositories(organization: String): Result<List<ResponseRepositoriesDto>>
}
