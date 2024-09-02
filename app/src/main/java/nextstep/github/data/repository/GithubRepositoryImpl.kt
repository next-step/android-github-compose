package nextstep.github.data.repository

import nextstep.github.data.source.GithubDataSource
import nextstep.github.model.GithubRepo

class GithubRepositoryImpl(
    private val githubDataSource: GithubDataSource,
) : GithubRepository {
    override suspend fun getRepositories(organization: String): List<GithubRepo> {
        return githubDataSource.getRepositories(organization)
    }
}
