package nextstep.github.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nextstep.github.core.data.GithubRepositoryInfo

@Serializable
data class RepositoryEntity(
    @SerialName("full_name") val fullName: String?,
    @SerialName("description") val description: String?,
) {
    fun RepositoryEntity.toRepositoryInfo() = GithubRepositoryInfo(
        fullName = fullName ?: "",
        description = description ?: ""
    )
}
