package nextstep.github.repository

import nextstep.github.data.repository.GithubRepository
import nextstep.github.model.GithubRepo

class FakeGithubRepository : GithubRepository {
    var shouldReturnException = false

    override suspend fun getRepositories(organization: String): List<GithubRepo> {
        if (shouldReturnException) {
            throw Exception("network error")
        }
        val dummyRepos = listOf(
            GithubRepo(
                id = 1,
                fullName = "octocat/Hello-World",
                description = "This is your first repository"
            ),
            GithubRepo(
                id = 2,
                fullName = "google/androidx",
                description = "Development environment for Android Jetpack extension libraries"
            ),
            GithubRepo(
                id = 3,
                fullName = "kotlin/kotlin",
                description = "The Kotlin Programming Language"
            ),
            GithubRepo(
                id = 4,
                fullName = "square/retrofit",
                description = "A type-safe HTTP client for Android and the JVM"
            ),
            GithubRepo(
                id = 5,
                fullName = "JetBrains/compose-multiplatform",
                description = null
            )
        )
        return dummyRepos
    }
}
