import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nextstep.github.core.model.RepositoryEntity

@Serializable
data class ResponseRepositoriesItem(
    @SerialName("id")
    val id: Long,
    @SerialName("full_name")
    val fullName: String?,
    @SerialName("description")
    val description: String?,
)

fun ResponseRepositoriesItem.toEntity() =
    RepositoryEntity(
        fullName = fullName ?: "",
        description = description ?: "",
    )
