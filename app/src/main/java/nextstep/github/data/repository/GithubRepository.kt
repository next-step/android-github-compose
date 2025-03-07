package nextstep.github.data.repository

import nextstep.github.data.model.RepositoryModel
import nextstep.github.data.remote.api.GithubService
import nextstep.github.data.remote.mapper.toDataList

interface GithubRepository {
    suspend fun getRepositories(organization: String): Result<List<RepositoryModel>>
}

class GithubRepositoryImpl(
    private val githubService: GithubService,
) : GithubRepository {

    override suspend fun getRepositories(organization: String): Result<List<RepositoryModel>> {
        return runCatching {
            githubService
                .getRepositories(organization = organization)
                .toDataList()
        }
    }
}
