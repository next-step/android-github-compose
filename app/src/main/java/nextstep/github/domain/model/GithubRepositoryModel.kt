package nextstep.github.domain.model

import nextstep.github.data.response.RepositoryResponse

data class GithubRepositoryModel(
    val fullName: String = "",
    val description: String = "",
    val stars: Int = 0,
) {
    val isHot: Boolean
        get() = stars >= 50
}

fun RepositoryResponse.toModel(): GithubRepositoryModel = GithubRepositoryModel(
    fullName = fullName,
    description = description,
    stars = stars,
)
