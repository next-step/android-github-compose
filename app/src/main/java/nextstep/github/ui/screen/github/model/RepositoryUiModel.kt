package nextstep.github.ui.screen.github.model

import nextstep.github.domain.model.Repository

data class RepositoryUiModel(
    val id: Int,
    val fullName: String,
    val description: String,
    val stars: Int,
    val isHot: Boolean,
)

fun List<Repository>.toUiStateList() = map { it.toUiState() }

fun Repository.toUiState() = RepositoryUiModel(
    id = id,
    fullName = fullName,
    description = description,
    stars = stars,
    isHot = isOverFiftyStars,
)
