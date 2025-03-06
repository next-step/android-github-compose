package nextstep.github.data.datasource.api

import nextstep.github.data.model.RepositoryDataModel

interface GithubDataSource {

    suspend fun getRepos(): List<RepositoryDataModel>
}