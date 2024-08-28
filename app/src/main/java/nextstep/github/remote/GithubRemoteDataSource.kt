package nextstep.github.remote

import nextstep.github.model.GithubRepositoryDto

interface GithubRemoteDataSource {
    suspend fun getRepositories(organization: String): List<GithubRepositoryDto>
}
