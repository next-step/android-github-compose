package nextstep.github.data.repository

import nextstep.github.data.api.GithubService

class GithubRepository(private val apiService: GithubService) {
    suspend fun getRepositories(organization: String) = apiService.getRepositories(organization)
}
