package nextstep.github.core.model

sealed interface NextStepRepository {
    val fullName: String
    val description: String
    val stars: Int

    data class Hot(
        override val fullName: String,
        override val description: String,
        override val stars: Int,
    ) : NextStepRepository

    data class Normal(
        override val fullName: String,
        override val description: String,
        override val stars: Int,
    ) : NextStepRepository

    companion object {
        const val HOT_STARS = 50
    }
}
