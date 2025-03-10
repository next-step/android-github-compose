package nextstep.github.data.repositories

import kotlinx.coroutines.flow.Flow
import nextstep.github.model.RepositoryResponse

interface GithubRepository {

    fun getRepositoriesStream(organization: String): Flow<List<RepositoryResponse>>
}
