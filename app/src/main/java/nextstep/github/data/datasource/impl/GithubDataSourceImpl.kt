package nextstep.github.data.datasource.impl

import nextstep.github.data.datasource.api.GithubDataSource
import nextstep.github.data.model.RepositoryDataModel
import nextstep.github.data.model.toDataModel
import nextstep.github.data.service.GithubService

class GithubDataSourceImpl(
    private val service: GithubService,
): GithubDataSource {

    override suspend fun getRepos(): List<RepositoryDataModel> {
        return service.getRepos().map { it.toDataModel() }
    }
}