package nextstep.github.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import nextstep.github.data.service.GithubService
import nextstep.github.ui.model.RepositoryModel
import nextstep.github.ui.model.toModel

class GithubRepositoryImpl(
    private val githubService: GithubService
) : GithubRepository {
    override fun getRepositories(organization: String): Flow<List<RepositoryModel>> = flow {
        emit(
            githubService.getRepositories(organization).map {
                it.toModel()
            }
        )
    }

}
