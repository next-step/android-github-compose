package nextstep.github.domain.entity

data class Repository(
    val fullName: String,
    val description: String,
    val stars: Int,
    val isHot: Boolean,
)
