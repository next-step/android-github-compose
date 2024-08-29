package nextstep.github.data

import nextstep.github.model.GithubRepositoryDto

interface GithubRepository {
    suspend fun getRepositories(organization: String): List<GithubRepositoryDto>
}
