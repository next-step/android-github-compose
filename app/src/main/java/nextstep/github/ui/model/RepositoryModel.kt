package nextstep.github.ui.model

import nextstep.github.data.entity.RepositoryEntity

data class RepositoryModel(
    val fullName: String,
    val description: String
)

fun RepositoryEntity.toModel(): RepositoryModel = RepositoryModel(
    fullName = fullName ?: "",
    description = description ?: ""
)
