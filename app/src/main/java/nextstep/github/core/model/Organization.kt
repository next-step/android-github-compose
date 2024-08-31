package nextstep.github.core.model

enum class Organization(
    val value: String,
) {
    NEXT_STEP("next-step"),
    ;

    companion object {
        fun fromValue(value: String?): Organization? = Organization.entries.firstOrNull { it.value == value }
    }
}
