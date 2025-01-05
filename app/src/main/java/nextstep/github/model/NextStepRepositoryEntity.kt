package nextstep.github.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NextStepRepositoryEntity(
    @SerialName("full_name") val fullName: String?,
    @SerialName("description") val description: String?,
    @SerialName("stargazers_count") val stars: Int?,
) {
    companion object {
        const val HOT_THRESHOLD = 50
    }

    fun isHot(count: Int): Boolean {
        return stars != null && stars >= count
    }
}
