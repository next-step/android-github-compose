package nextstep.github.data.remote

import nextstep.github.model.RepositoryEntity

interface GithubRemoteDataSource {

    suspend fun getRepositories(organization: String): List<RepositoryEntity>

}
