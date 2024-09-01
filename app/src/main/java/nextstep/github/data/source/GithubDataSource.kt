package nextstep.github.data.source

import nextstep.github.model.GithubRepo

interface GithubDataSource {
    suspend fun getRepositories(organization: String): List<GithubRepo>
}
