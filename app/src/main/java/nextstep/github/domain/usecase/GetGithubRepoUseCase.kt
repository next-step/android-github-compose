package nextstep.github.domain.usecase

import nextstep.github.core.data.GithubRepository
import nextstep.github.domain.entity.RepositoryEntity
import nextstep.github.core.network.ApiResult

class GetGithubRepoUseCase(
    private val githubRepository: GithubRepository
) {
    suspend operator fun invoke(organization: String): ApiResult<List<RepositoryEntity>> {
        val response = githubRepository.getRepositories(organization)

        return if (response.isSuccessful && response.body() != null) {
            val repositoryEntityList = response.body()!!.map {
                RepositoryEntity(
                    fullName = it.fullName ?: "",
                    description = it.description ?: "",
                    stars = it.stars ?: 0
                )
            }
            ApiResult.Success(repositoryEntityList)
        } else {
            ApiResult.Error(response.code(), Throwable(response.message()))
        }
    }
}
