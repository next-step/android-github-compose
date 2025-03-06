package nextstep.github.data

class GithubRepository(
    private val remoteDataSource: GithubRemoteDataSource,
    private val localDataSource: GithubLocalDataSource,
) {
    suspend fun getRepositories(organization: String) {
        remoteDataSource.fetchRepositories(organization)
    }
}