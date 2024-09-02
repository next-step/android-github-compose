package nextstep.github.core.model

data class RepositoryEntity(
    val fullName: String,
    val description: String,
    val stars: Int,
    val isHot: Boolean,
) {
    constructor(
        fullName: String,
        description: String,
        stars: Int,
    ) : this(fullName, description, stars, stars >= HOT_STARS)

    companion object {
        private const val HOT_STARS = 50
    }
}
