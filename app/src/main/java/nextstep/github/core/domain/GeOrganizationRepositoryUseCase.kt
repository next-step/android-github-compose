package nextstep.github.core.domain

import nextstep.github.core.data.GithubRepository
import nextstep.github.core.model.Organization
import nextstep.github.core.model.OrganizationRepository

class GeOrganizationRepositoryUseCase(
    private val githubRepository: GithubRepository,
) {
    suspend operator fun invoke(organization: Organization): Result<List<OrganizationRepository>> =
        githubRepository
            .getRepositories(Organization.NEXT_STEP)
            .map {
                it.map { item ->
                    if (item.stars >= OrganizationRepository.HOT_STARS) {
                        OrganizationRepository.Hot(item.fullName, item.description, item.stars)
                    } else {
                        OrganizationRepository.Normal(item.fullName, item.description, item.stars)
                    }
                }
            }
}
