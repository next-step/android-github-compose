package nextstep.github.data.remote.mapper

import nextstep.github.data.dto.RepositoryDto
import nextstep.github.data.remote.response.RepositoryResponse

fun List<RepositoryResponse>.toDataList(): List<RepositoryDto> = map { it.toData() }

fun RepositoryResponse.toData() = RepositoryDto(
    id = id,
    fullName = fullName ?: "",
    description = description ?: "",
    stars = stars ?: 0
)
