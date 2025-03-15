package nextstep.github.model

data class GitHubRepo(
    val id: Long,
    val fullName: String,
    val description: String?,
    val stars: Int,
) {
    val isHot: Boolean = stars >= HOT_REPO_STAR_COUNT_THRESHOLD

    companion object {
        private const val HOT_REPO_STAR_COUNT_THRESHOLD = 50
    }
}
