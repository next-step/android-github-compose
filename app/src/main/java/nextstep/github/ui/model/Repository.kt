package nextstep.github.ui.model

import nextstep.github.data.RepositoryEntity

data class Repository(
    val fullName: String,
    val description: String
)

fun RepositoryEntity.toRepository(): Repository {
    return Repository(
        fullName = this.fullName ?: "",
        description = this.description ?: ""
    )
}
