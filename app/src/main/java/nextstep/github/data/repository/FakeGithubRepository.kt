package nextstep.github.data.repository

import nextstep.github.data.model.RepositoryModel

class FakeGithubRepository : GithubRepository {

    private var shouldFail = false
    private var fakeData: List<RepositoryModel> = emptyList()

    fun setFakeData(data: List<RepositoryModel>) {
        shouldFail = false
        fakeData = data
    }

    fun setFailureMode(isFail: Boolean) {
        shouldFail = isFail
    }

    fun clearData() {
        fakeData = emptyList()
    }

    override suspend fun getRepositories(organization: String): Result<List<RepositoryModel>> {
        return if (shouldFail) {
            Result.failure(Exception("Fake network error"))
        } else {
            Result.success(fakeData)
        }
    }
}
