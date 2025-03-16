package nextstep.github.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import nextstep.github.data.repository.GithubRepository
import nextstep.github.domain.model.Repository

class GithubRepositoryUseCase(private val githubRepository: GithubRepository) {

    fun getRepositories(organization: String): Flow<RepositoryResult<List<Repository>>> = flow {
        githubRepository.getRepositories(organization)
            .onSuccess { repositories ->
                val data = repositories.map {
                    Repository(
                        id = it.id,
                        fullName = it.fullName,
                        description = it.description,
                        stars = it.stars,
                        isOverFiftyStars = it.stars > 50 // 50개 이상의 star를 가진 repository인지 확인
                    )
                }

                emit(RepositoryResult.Success(data))
            }.onFailure { exception ->
                emit(RepositoryResult.Error(exception))
            }
    }
}
