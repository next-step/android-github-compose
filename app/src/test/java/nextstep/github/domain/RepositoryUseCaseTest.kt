package nextstep.github.domain

import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest
import nextstep.github.data.FakeGithubRepository
import nextstep.github.data.GithubRepository
import nextstep.github.data.impl.GithubRepositoryImpl
import nextstep.github.domain.impl.RepositoryUseCaseImpl
import org.junit.After
import org.junit.Before
import org.junit.Test

class RepositoryUseCaseTest {
    private lateinit var useCase: RepositoryUseCase

    @Before
    fun setUp() {
        useCase = FakeRepositoryUseCase()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `유즈케이스 데이터 내려오는 경우 데이터가 비어있지 않은지 확인한다`(): Unit = runTest {
        val actual = useCase.getRepositories().fold(
            onSuccess = { it },
            onFailure = { emptyList() }
        )
        actual.forEach {
            println(it)
        }
        assertThat(actual.isEmpty()).isFalse()
    }
}