package nextstep.github.domain.model

import nextstep.github.data.repository.model.RepositoryEntity
import nextstep.github.util.orZero

data class Repository(
    val id: Long,
    val fullName: String,
    val description: String,
    val stars: Int,
) {
    val isHot: Boolean = stars >= HOT_STANDARD

    companion object {
        private const val HOT_STANDARD = 50
    }
}

fun RepositoryEntity.toDomain(): Repository {
    return Repository(
        id = id,
        fullName = fullName.orEmpty(),
        description = description.orEmpty(),
        stars = stars.orZero()
    )
}
