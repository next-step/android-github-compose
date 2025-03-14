package nextstep.github.data.repository

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import nextstep.github.data.api.GithubService
import nextstep.github.data.dto.toGithubRepo
import nextstep.github.model.GithubRepo

class GithubRepoRepository(
    private val githubService: GithubService
) {
    fun getRepos(
        organization: String,
    ): Flow<List<GithubRepo>> = flow {
        while (true) {
            val repositories = githubService.getRepositories(organization = organization)
            emit(repositories)
            delay(3000L)
        }
    }.map { dto -> dto.map { it.toGithubRepo() } }
}

