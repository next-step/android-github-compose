package nextstep.github.domain

import nextstep.github.data.repo.GithubRepository
import nextstep.github.domain.model.GithubRepo

class GetOrganizationReposUseCase(
    private val githubRepository: GithubRepository
) {

    suspend operator fun invoke(organization: String): Result<List<GithubRepo>> {
        return try {
            val result = githubRepository.fetchRepos(organization)
                .map {
                    GithubRepo(
                        fullName = it.fullName ?: "",
                        description = it.description ?: "",
                        stars = it.stars ?: 0,
                        isHotRepo = (it.stars ?: 0) >= 50
                    )
                }
            Result.success(result)
        } catch (e: Exception) {
            Result.failure(Exception("$organization Repositories not found"))
        }
    }

}
