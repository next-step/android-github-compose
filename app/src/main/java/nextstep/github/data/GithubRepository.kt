package nextstep.github.data

interface GithubRepository {
    suspend fun getRepositories(): List<RepositoryEntity>
}

class GithubRepositoryImpl(private val githubService: GithubService) : GithubRepository {

    override suspend fun getRepositories(): List<RepositoryEntity> {
        return githubService.getRepositories(ORGANIZATION_NAME)
    }

    companion object {
        private const val ORGANIZATION_NAME = "next-step"
    }
}
