package nextstep.github.data.repository.api

import nextstep.github.data.model.RepositoryResponseModel

interface GithubRepository {

    suspend fun getRepos(): List<RepositoryResponseModel>
}