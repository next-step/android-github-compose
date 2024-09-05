package nextstep.github.domain.model

data class GithubRepo(
    val fullName: String,
    val description: String,
    val stars: Int
) {
    val isHotRepo: Boolean
        get() = stars >= 50
}

val dummyDefaultGithubRepo =
    GithubRepo(
        fullName = "next-step/android-github-compose",
        description = "권장 앱 아키텍처",
        stars = 50
    )

val dummyGithubRepoHot =
    GithubRepo(
        fullName = "next-step/android-github-compose",
        description = "권장 앱 아키텍처",
        stars = 50
    )

val dummyGithubRepoNonHot =
    GithubRepo(
        fullName = "next-step/android-github-compose",
        description = "권장 앱 아키텍처",
        stars = 49
    )
