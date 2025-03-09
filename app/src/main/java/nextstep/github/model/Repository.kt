package nextstep.github.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Repository(
    @SerialName("id")
    val id: Long,

    @SerialName("full_name")
    val fullName: String,

    @SerialName("description")
    val description: String?,
)
