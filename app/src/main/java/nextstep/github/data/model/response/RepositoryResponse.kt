package nextstep.github.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryResponse(
    @SerialName("full_name") val fullName: String?,
    @SerialName("description") val description: String?,
)
