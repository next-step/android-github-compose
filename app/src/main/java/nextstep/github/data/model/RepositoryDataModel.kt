package nextstep.github.data.model

import nextstep.github.data.entity.Repository

data class RepositoryDataModel(
    val fullName: String,
    val description: String,
)

fun RepositoryDataModel.toEntity() = Repository(
    fullName = this.fullName,
    description = this.description,
)