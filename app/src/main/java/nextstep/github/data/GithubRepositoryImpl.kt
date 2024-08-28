package nextstep.github.data

import nextstep.github.model.GithubRepositoryDto
import nextstep.github.remote.GithubRemoteDataSource

class GithubRepositoryImpl(
    private val githubRemoteDataSource: GithubRemoteDataSource,
) : GithubRepository {
    override suspend fun getRepositories(organization: String): List<GithubRepositoryDto> {
        return githubRemoteDataSource.getRepositories(organization)
    }
}
