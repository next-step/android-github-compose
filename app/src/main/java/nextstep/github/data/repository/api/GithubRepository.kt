package nextstep.github.data.repository.api

import nextstep.github.domain.entity.Repository

interface GithubRepository {

    suspend fun getRepos(): List<Repository>
}