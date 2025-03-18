package nextstep.github.domain.model

data class Repository (
    val id: Int,
    val fullName: String,
    val description: String,
    val stars: Int,
    val isOverFiftyStars: Boolean = stars > 50,
)
