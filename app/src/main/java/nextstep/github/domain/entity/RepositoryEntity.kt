package nextstep.github.domain.entity

data class RepositoryEntity(
    val fullName: String,
    val description: String,
    val stars: Int
) {
    val isHot: Boolean by lazy { stars > HOT_COUNT }

    companion object {
        private const val HOT_COUNT = 50
    }
}
