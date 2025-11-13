package nextstep.github.presentation.mapper

import nextstep.github.data.model.RepositoryEntity
import nextstep.github.presentation.repositorylist.Repository

fun RepositoryEntity.toUi(): Repository {
    return Repository(
        fullName = fullName ?: "",
        description = description ?: "",
    )
}
