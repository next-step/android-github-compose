package nextstep.github.ui.model

import nextstep.github.data.GithubRepositoryEntity

data class GithubRepositoryModel(
    val id: Int,
    val fullName: String?,
    val description: String?,
    val starCount: Int?,
) {
    fun showHotKeyword(): Boolean {
        return (starCount ?: 0) >= HOT_REPOSITORY_LIMIT_COUNT
    }
}

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

private const val HOT_REPOSITORY_LIMIT_COUNT = 50
