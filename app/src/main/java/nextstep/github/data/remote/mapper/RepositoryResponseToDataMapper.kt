package nextstep.github.data.remote.mapper

import nextstep.github.data.model.RepositoryModel
import nextstep.github.data.remote.response.RepositoryResponse

fun List<RepositoryResponse>.toDataList(): List<RepositoryModel> = map { it.toData() }

fun RepositoryResponse.toData() = RepositoryModel(
    id = id,
    fullName = fullName ?: "",
    description = description ?: ""
)
