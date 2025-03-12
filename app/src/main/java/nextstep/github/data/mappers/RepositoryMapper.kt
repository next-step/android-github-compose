package nextstep.github.data.mappers

import nextstep.github.data.responses.RepositoryResponse
import nextstep.github.model.Repository

fun RepositoryResponse.toDomain() = Repository(
    id = id,
    fullName = fullName,
    description = description,
)
