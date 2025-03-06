package nextstep.github.data.remote.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubResponse(
    @SerialName("full_name") val fullName: String?,
    @SerialName("description") val description: String?,
)
