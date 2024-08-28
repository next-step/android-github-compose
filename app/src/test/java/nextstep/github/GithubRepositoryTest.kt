package nextstep.github

import nextstep.github.data.GithubRepository
import nextstep.github.data.GithubRepositoryImpl
import nextstep.github.model.GithubRepositoryDto
import nextstep.github.remote.GithubRemoteDataSource
import nextstep.github.remote.GithubRemoteDataSourceImpl
import nextstep.github.remote.api.GithubApiService
import org.junit.Test
import kotlinx.coroutines.test.runTest

class GithubRepositoryTest {

    private val githubApiService: GithubApiService = object : GithubApiService {
        override suspend fun getRepositories(organization: String): List<GithubRepositoryDto> {
            return listOf(
                GithubRepositoryDto(
                    fullName = "Test1 Repository",
                    description = "test1 repository",
                ),
                GithubRepositoryDto(
                    fullName = "Test2 Repository",
                    description = "test2 repository",
                ),
                GithubRepositoryDto(
                    fullName = "Test3 Repository",
                    description = "test3 repository",
                ),
            )
        }
    }
    private val githubRemoteDataSource: GithubRemoteDataSource = GithubRemoteDataSourceImpl(githubApiService)
    private val githubRepository: GithubRepository = GithubRepositoryImpl(githubRemoteDataSource)

    @Test
    fun `api응답을 그대로 잘 전달`() = runTest{
        val response = githubRepository.getRepositories("next-step")
        assert(response.size == 3)
        assert(response[0].fullName == "Test1 Repository")
        assert(response[0].description == "test1 repository")
        assert(response[1].fullName == "Test2 Repository")
        assert(response[1].description == "test2 repository")
        assert(response[2].fullName == "Test3 Repository")
        assert(response[2].description == "test3 repository")
    }
}
