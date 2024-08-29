package nextstep.github.data

class ExampleRepository(
    private val githubService: GithubService
) {
    suspend fun getRepositories(organization: String): List<RepositoryEntity> {
        return githubService.getRepositories(organization)
    }
}