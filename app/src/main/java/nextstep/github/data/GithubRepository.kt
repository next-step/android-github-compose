package nextstep.github.data

interface GithubRepository {
    suspend fun getRepositories(): Result<List<RepositoryEntity>>
}
