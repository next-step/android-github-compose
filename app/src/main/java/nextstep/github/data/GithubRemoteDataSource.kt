package nextstep.github.data

import retrofit2.Retrofit

class GithubRemoteDataSource {
    private val retrofit = Retrofit.Builder()
        .baseUrl(GITHUB_BASE_URL)
        .build()
    private val githubService = retrofit.create(GithubService::class.java)

    suspend fun fetchRepositories(organization: String): Result<List<RepositoryEntity>> {
        return githubService.getRepositories(organization)
    }
}

private const val GITHUB_BASE_URL = "https://api.github.com"