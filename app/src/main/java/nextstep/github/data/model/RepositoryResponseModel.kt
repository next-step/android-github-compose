package nextstep.github.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nextstep.github.data.entity.Repository

@Serializable
data class RepositoryResponseModel(
    @SerialName("full_name") val fullName: String?,
    @SerialName("description") val description: String?,
    @SerialName("stargazers_count") val stars: Int?,
)

fun RepositoryResponseModel.toEntity() = Repository(
    fullName = this.fullName.orEmpty(),
    description = this.description.orEmpty(),
    stars = stars ?: 0,
)