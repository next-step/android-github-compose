package nextstep.github.ui.model

import nextstep.github.data.model.response.RepositoryResponse

data class RepositoryModel(
    val fullName: String,
    val description: String
)

fun RepositoryResponse.toModel(): RepositoryModel = RepositoryModel(
    fullName = fullName ?: "",
    description = description ?: ""
)
