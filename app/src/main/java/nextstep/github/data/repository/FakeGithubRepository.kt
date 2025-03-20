package nextstep.github.data.repository

import nextstep.github.data.dto.RepositoryDto

class FakeGithubRepository : GithubRepository {

    private var shouldFail = false
    private var fakeData: List<RepositoryDto> = emptyList()

    fun setFakeData(data: List<RepositoryDto>) {
        shouldFail = false
        fakeData = data
    }

    fun setFailureMode(isFail: Boolean) {
        shouldFail = isFail
    }

    override suspend fun getRepositories(organization: String): Result<List<RepositoryDto>> {
        return if (shouldFail) {
            Result.failure(Exception("Fake network error"))
        } else {
            Result.success(fakeData)
        }
    }
}
