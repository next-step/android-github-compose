package nextstep.github.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nextstep.github.model.GithubRepo

@Serializable
data class GithubRepoResponse(
    @SerialName("id")
    val id: Long,
    @SerialName("full_name")
    val fullName: String?,
    @SerialName("description")
    val description: String?,
)

fun GithubRepoResponse.toGithubRepo() = GithubRepo(
    id = id,
    fullName = fullName,
    description = description,
)