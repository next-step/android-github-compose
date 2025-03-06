package nextstep.github.data.repository.api

import nextstep.github.data.entity.Repository

interface GithubRepository {

    suspend fun getRepos(): List<Repository>
}