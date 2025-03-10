package nextstep.github.data.mapper

import nextstep.github.data.api.model.RepositoryEntity
import nextstep.github.model.Repository


fun RepositoryEntity.toRepository(): Repository =
    Repository(
        id = id,
        fullName = fullName,
        description = description
    )