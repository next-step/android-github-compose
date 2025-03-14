package nextstep.github.data.mappers

import nextstep.github.data.responses.GitHubRepoResponse
import nextstep.github.model.GitHubRepo

fun GitHubRepoResponse.toDomain() = GitHubRepo(
    id = id,
    fullName = fullName,
    description = description,
)
