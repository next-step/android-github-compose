package nextstep.github.data.repository

import nextstep.github.model.Repository

interface GithubRepository {

    suspend fun getRepositories(organization: String): List<Repository>
}