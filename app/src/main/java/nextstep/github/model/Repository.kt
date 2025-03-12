package nextstep.github.model

data class Repository(
    val id: Int,
    val fullName: String,
    val description: String,
    val stars: Int
) {
    fun isHotRepository(): Boolean =
        stars >= 50
}
