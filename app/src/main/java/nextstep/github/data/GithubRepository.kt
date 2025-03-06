package nextstep.github.data

interface GithubRepository {
    suspend fun getRepositories(organization: String): List<RepositoryEntity>
}

class GithubRepositoryImpl(private val githubService: GithubService) : GithubRepository {

    override suspend fun getRepositories(organization: String): List<RepositoryEntity> {
        return githubService.getRepositories(organization)
    }

}