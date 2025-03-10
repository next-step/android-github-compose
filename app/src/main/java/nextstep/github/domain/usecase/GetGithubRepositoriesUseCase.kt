package nextstep.github.domain.usecase

import kotlinx.coroutines.flow.flow
import nextstep.github.data.repository.GithubRepository
import nextstep.github.util.asResult

class GetGithubRepositoriesUseCase(private val githubRepository: GithubRepository) {

    operator fun invoke(organization: String) = flow {
        emit(githubRepository.getRepositories(organization))
    }.asResult()

}