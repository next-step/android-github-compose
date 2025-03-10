package nextstep.github.data

import nextstep.github.network.Result

class GithubRemoteDataSource(
    private val githubService: GithubService
) {
    suspend fun fetchRepositories(
        organization: String,
        onPreLoad: () -> Unit = {},
    ): Result<List<GithubRepositoryEntity>> {
        return githubService
            .also { onPreLoad.invoke() }
            .getRepositories(organization)
    }
}