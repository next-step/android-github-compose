package nextstep.github.data

import AppContainer
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.test.runTest

import org.junit.After
import org.junit.Before
import org.junit.Test

class ExampleRepositoryTest {
    private lateinit var appContainer : AppContainer
    private lateinit var repository: ExampleRepository

    @Before
    fun setUp() {
        appContainer = AppContainer()
        repository = appContainer.exampleRepository
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `리파지토리 데이터가 비어있지 않은지 확인해 본다`(): Unit = runTest {
        val actual = repository.getRepositories("next-step")
        actual.forEach {
            println(it)
        }
        assertThat(actual.isEmpty()).isFalse()
    }
}