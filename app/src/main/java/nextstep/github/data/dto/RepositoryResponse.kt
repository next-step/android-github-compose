package nextstep.github.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryResponse(
    @SerialName("full_name") val fullName: String?,
    @SerialName("description") val description: String?,
)
