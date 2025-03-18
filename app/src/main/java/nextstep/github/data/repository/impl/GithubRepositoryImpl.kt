package nextstep.github.data.repository.impl

import nextstep.github.data.datasource.api.GithubDataSource
import nextstep.github.domain.entity.Repository
import nextstep.github.data.model.toEntity
import nextstep.github.data.repository.api.GithubRepository

class GithubRepositoryImpl(
    private val dataSource: GithubDataSource,
): GithubRepository {
    override suspend fun getRepos(): List<Repository> {
        return dataSource.getRepos().map { it.toEntity() }
    }
}