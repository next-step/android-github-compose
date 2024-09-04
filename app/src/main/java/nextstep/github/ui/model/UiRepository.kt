package nextstep.github.ui.model

import nextstep.github.model.RepositoryEntity

data class UiRepository(
    val fullName: String,
    val description: String?,
    val stars: Int,
) {
    val isHot: Boolean = stars >= 50
}

fun RepositoryEntity.toUiModel() = UiRepository(
    fullName = this.fullName ?: "",
    description = this.description,
    stars = this.stars ?: 0,
)
