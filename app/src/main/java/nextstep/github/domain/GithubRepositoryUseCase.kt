package nextstep.github.domain

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import nextstep.github.data.repository.GithubRepository
import nextstep.github.domain.model.Repository

class GithubRepositoryUseCase(private val githubRepository: GithubRepository) {

    fun getRepositories(organization: String): Flow<RepositoryResult<List<Repository>>> =
        flow<RepositoryResult<List<Repository>>> {
            val data = githubRepository.getRepositories(organization)
                .map {
                    it.map { repositoryDto ->
                        Repository(
                            id = repositoryDto.id,
                            fullName = repositoryDto.fullName,
                            description = repositoryDto.description,
                            stars = repositoryDto.stars,
                        )
                    }
                }.getOrThrow()

            emit(RepositoryResult.Success(data))
        }.catch {
            emit(RepositoryResult.Error(it))
        }
}
