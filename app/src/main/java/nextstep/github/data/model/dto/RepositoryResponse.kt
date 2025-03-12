package nextstep.github.data.model.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryResponse(
    @SerialName("id") val id: Int,
    @SerialName("full_name") val fullName: String,
    @SerialName("description") val description: String?,
)
