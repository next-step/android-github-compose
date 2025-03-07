package nextstep.github.data.remote.mapper

import nextstep.github.data.model.GithubModel
import nextstep.github.data.remote.response.RepositoryResponse

fun List<RepositoryResponse>.toDataList(): List<GithubModel> = map { it.toData() }

fun RepositoryResponse.toData() = GithubModel(
    fullName = fullName ?: "",
    description = description ?: ""
)
