package nextstep.github.core.model

sealed interface OrganizationRepository {
    val fullName: String
    val description: String
    val stars: Int

    data class Hot(
        override val fullName: String,
        override val description: String,
        override val stars: Int,
    ) : OrganizationRepository

    data class Normal(
        override val fullName: String,
        override val description: String,
        override val stars: Int,
    ) : OrganizationRepository

    companion object {
        const val HOT_STARS = 50
    }
}
