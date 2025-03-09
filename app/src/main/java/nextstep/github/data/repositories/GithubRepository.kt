package nextstep.github.data.repositories

import nextstep.github.model.Repository

interface GithubRepository {

    suspend fun getRepositories(organization: String): List<Repository>
}
