package nextstep.github.ui.model

import nextstep.github.data.GithubRepositoryEntity

data class GithubRepositoryModel(
    val id: Int,
    val fullName: String?,
    val description: String?,
    val starCount: Int?,
)

fun GithubRepositoryEntity.toGithubRepositoryModel(): GithubRepositoryModel? {
    return if (id == null) null else {
        GithubRepositoryModel(
            id = this.id,
            fullName = this.fullName,
            description = this.description,
            starCount = this.starCount,
        )
    }
}