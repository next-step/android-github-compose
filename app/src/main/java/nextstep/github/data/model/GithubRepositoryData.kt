package nextstep.github.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GithubRepositoryData(
    @SerialName("full_name") val fullName: String?,
    @SerialName("description") val description: String?,
    @SerialName("stargazers_count") val stars: Int?,
)
