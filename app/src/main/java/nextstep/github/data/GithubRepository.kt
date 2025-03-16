package nextstep.github.data

interface GithubRepository {
    suspend fun getRepositories(): List<RepositoryEntity>
}

class GithubRepositoryImpl(private val githubService: GithubService) : GithubRepository {

    override suspend fun getRepositories(): List<RepositoryEntity> {
        return githubService.getRepositories(Const.ORGANIZATION_NAME)
    }
}
