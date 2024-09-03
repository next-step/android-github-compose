package nextstep.github.data.source

import nextstep.github.data.model.toGithubRepo
import nextstep.github.data.service.GithubService
import nextstep.github.model.GithubRepo

class GithubRemoteDataSource(
    private val githubService: GithubService,
) : GithubDataSource {
    override suspend fun getRepositories(organization: String): List<GithubRepo> {
        val responseList = githubService.getRepositories(organization)
        return responseList.map { it.toGithubRepo() }
    }
}
