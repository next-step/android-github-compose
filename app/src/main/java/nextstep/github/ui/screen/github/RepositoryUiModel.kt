package nextstep.github.ui.screen.github

import nextstep.github.domain.model.Repository

data class RepositoryUiModel(
    val id: Int,
    val fullName: String,
    val description: String,
    val stars: Int = 0,
    val isOverFiftyStars: Boolean = false,
)

fun List<Repository>.toUiStateList() = map { it.toUiState() }

fun Repository.toUiState() = RepositoryUiModel(
    id = id,
    fullName = fullName,
    description = description,
    stars = stars,
    isOverFiftyStars = isOverFiftyStars,
)
