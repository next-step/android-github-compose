package nextstep.github.core.data

import nextstep.github.core.model.RepositoryEntity
import retrofit2.Response


interface GithubRepository {
    suspend fun getRepositories(organization: String): Response<List<RepositoryEntity>>
}
