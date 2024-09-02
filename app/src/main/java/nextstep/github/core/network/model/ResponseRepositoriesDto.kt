import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nextstep.github.core.model.RepositoryEntity

@Serializable
data class ResponseRepositoriesDto(
    @SerialName("id")
    val id: Long,
    @SerialName("full_name")
    val fullName: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("stargazers_count")
    val stars: Int?,
)

fun ResponseRepositoriesDto.toEntity() =
    RepositoryEntity(
        fullName = fullName ?: "",
        description = description ?: "",
        stars = stars ?: 0,
    )
