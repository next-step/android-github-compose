package nextstep.github.domain

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import nextstep.github.data.GithubRepository

class GetGithubRepositoryUseCase(
    private val githubRepository: GithubRepository,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO,
) {
    suspend operator fun invoke(organization: String, preLoad: () -> Unit = {}) =
        withContext(defaultDispatcher) {
            githubRepository.getRepositories(organization = organization, onPreLoad = preLoad)
        }
}