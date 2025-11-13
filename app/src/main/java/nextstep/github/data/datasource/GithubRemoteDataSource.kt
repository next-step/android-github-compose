package nextstep.github.data.datasource

import nextstep.github.data.model.RepositoryEntity

interface GithubRemoteDataSource {

    suspend fun getRepositories(organization: String): List<RepositoryEntity>
}
