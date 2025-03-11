package nextstep.github.data.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryEntity(
    @SerialName("id")
    val id: Int,
    @SerialName("full_name")
    val fullName: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("stargazers_count")
    val stars: Int?,
)
