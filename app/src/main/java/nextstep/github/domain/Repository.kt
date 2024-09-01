package nextstep.github.domain

data class Repository(
    val fullName: String,
    val description: String,
    val star: Int = 0,
) {
    val isHot: Boolean
        get() = star >= 50
}
