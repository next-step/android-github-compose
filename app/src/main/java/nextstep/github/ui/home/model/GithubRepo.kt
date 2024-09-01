package nextstep.github.ui.home.model

import nextstep.github.data.model.GithubReposResponse

data class GithubRepo(
    val fullName: String,
    val description: String,
) {
    companion object {
        fun fromResponse(data: List<GithubReposResponse>): List<GithubRepo> {
            return data.map { GithubRepo(it.fullName ?: "", it.description ?: "") }
        }
    }
}

val dummyData = listOf(
    GithubRepo(
        fullName = "next-step/android-github-compose",
        description = "권장 앱 아키텍처"
    )
)
