package nextstep.github.data.repository.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryEntity(
    @SerialName("id") val id: Long,
    @SerialName("full_name") val fullName: String?,
    @SerialName("description") val description: String?,
    @SerialName("stargazers_count") val stars: Int?,
)
