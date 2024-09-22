package nextstep.github.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class RemoteGitHubRepoInfo(
    @SerialName("full_name")
    val fullName: String?,
    @SerialName("description")
    val description: String?,
)