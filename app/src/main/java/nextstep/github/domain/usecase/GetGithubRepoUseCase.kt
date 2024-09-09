package nextstep.github.domain.usecase

import nextstep.github.core.data.GithubRepository
import nextstep.github.core.data.GithubRepositoryInfo
import nextstep.github.core.network.ApiResult

class GetGithubRepoUseCase(
    private val githubRepository: GithubRepository
) {
    suspend operator fun invoke(organization: String): ApiResult<List<GithubRepositoryInfo>> {
        val response = githubRepository.getRepositories(organization)

        return if (response.isSuccessful && response.body() != null) {
            val githubRepositoryInfoList = response.body()!!.map {
                GithubRepositoryInfo(
                    fullName = it.fullName ?: "",
                    description = it.description ?: "",
                    stars = it.stars ?: 0
                )
            }
            ApiResult.Success(githubRepositoryInfoList)
        } else {
            ApiResult.Error(response.code(), Throwable(response.message()))
        }
    }
}
