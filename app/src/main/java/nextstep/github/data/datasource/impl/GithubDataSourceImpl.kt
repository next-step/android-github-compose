package nextstep.github.data.datasource.impl

import nextstep.github.data.datasource.api.GithubDataSource
import nextstep.github.data.model.RepositoryResponseModel
import nextstep.github.data.service.GithubService

class GithubDataSourceImpl(
    private val service: GithubService,
): GithubDataSource {

    override suspend fun getRepos(): List<RepositoryResponseModel> {
        return service.getRepos()
    }
}