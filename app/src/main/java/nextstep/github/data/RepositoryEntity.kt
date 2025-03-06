package nextstep.github.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryEntity(
    @SerialName("full_name") val fullName: String?,
    @SerialName("description") val description: String?,
)