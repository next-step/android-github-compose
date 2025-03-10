package nextstep.github.data.repositories.impls

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import nextstep.github.data.repositories.GithubRepository
import nextstep.github.data.services.GithubService
import nextstep.github.model.RepositoryResponse

class RemoteGithubRepository(
    private val githubService: GithubService,
) : GithubRepository {
    override fun getRepositoriesStream(organization: String): Flow<List<RepositoryResponse>> =
        flow {
            emit(githubService.getRepositories(organization = organization))
        }
}
