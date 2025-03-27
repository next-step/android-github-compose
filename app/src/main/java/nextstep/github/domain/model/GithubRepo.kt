package nextstep.github.domain.model

data class GithubRepo(
    val fullName: String,
    val description: String,
    val stargazersCount: StargazersCount,
)
