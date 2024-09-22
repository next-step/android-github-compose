package nextstep.github.ui.usecase

import nextstep.github.data.model.RemoteGitHubRepoInfo
import nextstep.github.data.repository.GitHubRepository
import nextstep.github.ui.model.UiGitHubRepoInfo

internal interface GetGitHubRepositoryUseCase {
    suspend operator fun invoke(organization: String): List<UiGitHubRepoInfo>
}

internal class GetGitHubRepositoryUseCaseImpl(
    private val gitHubRepository: GitHubRepository
) : GetGitHubRepositoryUseCase {
    override suspend fun invoke(organization: String): List<UiGitHubRepoInfo> {
        return gitHubRepository.getRepositories(organization).map(RemoteGitHubRepoInfo::toUi)
    }
}

private fun RemoteGitHubRepoInfo.toUi(): UiGitHubRepoInfo {
    return UiGitHubRepoInfo(
        fullName = fullName ?: "",
        description = description ?: ""
    )
}