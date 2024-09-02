package nextstep.github.data.repository

import nextstep.github.model.GithubRepo

interface GithubRepository {
    suspend fun getRepositories(organization: String): List<GithubRepo>
}
