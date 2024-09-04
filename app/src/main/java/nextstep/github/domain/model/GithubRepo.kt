package nextstep.github.domain.model

data class GithubRepo(
    val fullName: String,
    val description: String,
    val stars: Int,
    val isHotRepo: Boolean
)

val dummyGithubRepo1 =
    GithubRepo(
        fullName = "next-step/android-github-compose",
        description = "권장 앱 아키텍처",
        stars = 50,
        isHotRepo = true
    )

val dummyGithubRepo2 =
    GithubRepo(
        fullName = "next-step/android-github-compose",
        description = "권장 앱 아키텍처",
        stars = 49,
        isHotRepo = false
    )
