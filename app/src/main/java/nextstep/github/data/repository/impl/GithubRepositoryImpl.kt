package nextstep.github.data.repository.impl

import nextstep.github.data.datasource.api.GithubDataSource
import nextstep.github.data.model.RepositoryResponseModel
import nextstep.github.data.repository.api.GithubRepository

class GithubRepositoryImpl(
    private val dataSource: GithubDataSource,
): GithubRepository {
    override suspend fun getRepos(): List<RepositoryResponseModel> {
        return dataSource.getRepos()
    }
}