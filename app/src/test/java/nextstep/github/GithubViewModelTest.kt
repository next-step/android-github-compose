package nextstep.github

import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import nextstep.github.data.repository.GithubRepository
import nextstep.github.domain.usecase.GetGithubRepositoriesUseCase
import nextstep.github.model.Repository
import nextstep.github.ui.github.GithubUiState
import nextstep.github.ui.github.GithubViewModel
import org.junit.Test

class GithubViewModelTest {

    @Test
    fun `초기값은_UiState의_상태값이_Loading_이어야_한다`() = runTest {
        //given
        val repositories = listOf(
            Repository(
                id = 1,
                fullName = "next-step/nextstep-docs1",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소1"
            ),
            Repository(
                id = 2,
                fullName = "next-step/nextstep-docs2",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소2"
            )
        )
        val getGithubRepositoriesUseCase = fakeGetGithubRepositoriesUseCase(repositories)

        val githubViewModel = GithubViewModel(getGithubRepositoriesUseCase)

        //then
        assertEquals(githubViewModel.uiState.value, GithubUiState.Loading)
    }

    @Test
    fun `결과값이_비어있는경우_UiState의_상태값이_EmptyRepository_이어야_한다`() = runTest {
        val getGithubRepositoriesUseCase = fakeGetGithubRepositoriesUseCase(emptyList())

        val githubViewModel = GithubViewModel(getGithubRepositoriesUseCase)

        //then
        assertEquals(githubViewModel.uiState.take(2).last(), GithubUiState.EmptyRepository)
    }

    @Test
    fun `결과값이_있는경우_UiState의_상태값이_Repositories_이어야_한다`() = runTest {

        //given
        val repositories = listOf(
            Repository(
                id = 11,
                fullName = "next-step/nextstep-docs11",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소11"
            ),
            Repository(
                id = 22,
                fullName = "next-step/nextstep-docs22",
                description = "nextstep 매뉴얼 및 문서를 관리하는 저장소22"
            )
        )

        val getGithubRepositoriesUseCase = fakeGetGithubRepositoriesUseCase(repositories)

        val githubViewModel = GithubViewModel(getGithubRepositoriesUseCase)

        //then
        assertEquals(
            githubViewModel.uiState.take(2).last(),
            GithubUiState.Repositories(repositories)
        )
    }

    @Test
    fun `결과값이_에러인경우_ErrorFlow가_에러를_올바르게_방출해야_한다`() = runTest {

        val exception = Exception("에러 발생")

        val githubRepository = object : GithubRepository {
            override suspend fun getRepositories(organization: String): List<Repository> =
                throw exception
        }

        val getGithubRepositoriesUseCase = GetGithubRepositoriesUseCase(githubRepository)

        val githubViewModel = GithubViewModel(getGithubRepositoriesUseCase)

        githubViewModel.errorFlow.take(1).collectLatest { error ->
            assertEquals(error, exception)
        }
    }


    companion object {

        fun fakeGetGithubRepositoriesUseCase(repositories: List<Repository>): GetGithubRepositoriesUseCase =
            GetGithubRepositoriesUseCase(fakeGithubRepository(repositories))

        private fun fakeGithubRepository(repositories: List<Repository>): GithubRepository =
            object : GithubRepository {
                override suspend fun getRepositories(organization: String): List<Repository> =
                    repositories
            }
    }
}