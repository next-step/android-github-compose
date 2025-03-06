package nextstep.github.data.remote.api.mapper

import nextstep.github.data.model.GithubModel
import nextstep.github.data.remote.response.GithubResponse

fun List<GithubResponse>.toDataList(): List<GithubModel> = map { it.toData() }

fun GithubResponse.toData() = GithubModel(
    fullName = fullName ?: "",
    description = description ?: ""
)
