package nextstep.github.data.datasource.api

import nextstep.github.data.model.RepositoryResponseModel

interface GithubDataSource {

    suspend fun getRepos(): List<RepositoryResponseModel>
}