package nextstep.github.data

interface GithubRepository {
    suspend fun getRepositories(organization: String): Result<List<RepositoryEntity>>
}
