package nextstep.github.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RepositoryResponseModel(
    @SerialName("full_name") val fullName: String?,
    @SerialName("description") val description: String?,
)

fun RepositoryResponseModel.toDataModel() = RepositoryDataModel(
    fullName = this.fullName.orEmpty(),
    description = this.description.orEmpty(),
)