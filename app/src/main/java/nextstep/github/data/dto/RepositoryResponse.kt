package nextstep.github.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nextstep.github.model.GithubRepo

@Serializable
data class RepositoryResponse(
    @SerialName("full_name") val fullName: String?,
    @SerialName("description") val description: String?,
)

fun RepositoryResponse.toGithubRepo() = GithubRepo(
    fullName = this.fullName ?: "",
    description = this.description ?: "",
)