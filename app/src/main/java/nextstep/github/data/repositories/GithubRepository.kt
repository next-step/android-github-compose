package nextstep.github.data.repositories

import kotlinx.coroutines.flow.Flow
import nextstep.github.model.Repository

interface GithubRepository {

    fun getRepositories(organization: String): Flow<List<Repository>>
}
