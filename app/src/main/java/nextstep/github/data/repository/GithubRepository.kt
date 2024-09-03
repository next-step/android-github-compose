package nextstep.github.data.repository

import kotlinx.coroutines.flow.Flow
import nextstep.github.ui.model.RepositoryModel

interface GithubRepository {
    fun getRepositories(organization: String): Flow<List<RepositoryModel>>
}
