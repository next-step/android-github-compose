package nextstep.github.data.repo

import nextstep.github.data.model.GithubReposResponse

interface GithubRepository {
    suspend fun fetchRepos(organization: String): List<GithubReposResponse>
}
