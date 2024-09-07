package nextstep.github.ui.model

import nextstep.github.model.GithubRepositoryDto

private const val HOT_MINIMUM_STARS = 50

data class GithubRepositoryModel(
    val fullName: String,
    val description: String,
    val stars: Int,
    val isHot: Boolean,
)

fun GithubRepositoryDto.toUiModel(): GithubRepositoryModel =
    GithubRepositoryModel(
        fullName = fullName,
        description = description.orEmpty(),
        stars = stars ?: 0,
        isHot = (stars ?: 0) >= HOT_MINIMUM_STARS,
    )
