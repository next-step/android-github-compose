package nextstep.github.data.repositories.impls

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import nextstep.github.data.mappers.toDomain
import nextstep.github.data.repositories.GithubRepoRepository
import nextstep.github.data.responses.GitHubRepoResponse
import nextstep.github.data.services.GithubRepoService
import nextstep.github.model.GitHubRepo

class RemoteGithubRepoRepository(
    private val githubRepoService: GithubRepoService,
) : GithubRepoRepository {
    override fun getGitHubReposStream(organization: String): Flow<List<GitHubRepo>> =
        flow {
            emit(
                githubRepoService
                    .getGitHubRepos(organization = organization)
                    .map(GitHubRepoResponse::toDomain)
            )
        }
}
