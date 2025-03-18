package nextstep.github.ui.model

import nextstep.github.domain.entity.Repository

data class RepositoryUiModel(
    val fullName: String,
    val description: String,
    val stars: Int,
    val isHot: Boolean,
)

fun Repository.toUiModel(isHot: Boolean): RepositoryUiModel {
    return RepositoryUiModel(
        fullName = fullName,
        description = description,
        stars = stars,
        isHot = isHot,
    )
}