import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.hasAnyDescendant
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.onParent
import androidx.compose.ui.test.performClick
import kotlinx.coroutines.runBlocking
import nextstep.github.data.dto.RepositoryDto
import nextstep.github.data.repository.FakeGithubRepository
import nextstep.github.domain.GithubRepositoryUseCase
import nextstep.github.ui.screen.github.GithubScreen
import nextstep.github.ui.screen.github.GithubViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class StatefulGithubScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var fakeViewModel: GithubViewModel
    private lateinit var repository: FakeGithubRepository
    private lateinit var useCase: GithubRepositoryUseCase
    private val data = listOf(
        RepositoryDto(
            id = 1,
            fullName = "next-step/nextstep-docs",
            description = "nextstep 매뉴얼 및 문서를 관리하는 저장소",
            stars = 50,
        ),
        RepositoryDto(
            id = 2,
            fullName = "next-step/holy-moly",
            description = "nextstep 홀리몰리한 저장소",
            stars = 100,
        ),
        RepositoryDto(
            id = 3,
            fullName = "next-step/haly-galy",
            description = "nextstep 할리갈리한 저장소",
            stars = 49,
        ),
    )

    @Before
    fun setUp() {
        // FakeGithubRepository 생성
        repository = FakeGithubRepository()

        // GithubRepositoryUseCase FakeGithubRepository 주입 및 생성
        useCase = GithubRepositoryUseCase(repository)

        // GithubViewModel FakeGithubRepository 주입 및 생성
        fakeViewModel = GithubViewModel(useCase)
    }

    @Test
    fun Github_데이터를_정상적으로_불러왔을떄_데이터가_화면에_표시된다() {
        // 데이터 세팅
        repository.setFakeData(data)

        composeTestRule.setContent {
            GithubScreen(viewModel = fakeViewModel)
        }

        // 데이터 로드
        runBlocking {
            fakeViewModel.fetchRepositories("next-step")
        }

        // 데이터 출력 검증
        data.forEach {
            composeTestRule.onNodeWithText(it.fullName)
                .assertExists()
        }
    }

    @Test
    fun Github_데이터가_비어있을때_화면에_메시지가_출력된다() {
        composeTestRule.setContent {
            GithubScreen(viewModel = fakeViewModel)
        }

        // 데이터 로드
        runBlocking {
            fakeViewModel.fetchRepositories("next-step")
        }

        // 빈 데이터 메시지 출력 검증
        composeTestRule.onAllNodesWithText("목록이 비었습니다.")
            .onFirst()
            .assertExists()
    }

    @Test
    fun Github_데이터를_로드중_에러_발생시_에러_스낵바를_호출한다() {
        // 데이터 로드 실패 세팅
        repository.setFailureMode(true)

        composeTestRule.setContent {
            GithubScreen(viewModel = fakeViewModel)
        }

        // 데이터 로드
        runBlocking {
            fakeViewModel.fetchRepositories("next-step")
        }

        // 에러 발생 스낵바 호출 검증
        composeTestRule.onNodeWithContentDescription("Snackbar")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("예상치 못한 오류가 발생했습니다.")
            .assertIsDisplayed()

        composeTestRule.onNodeWithText("재시도")
            .assertIsDisplayed()
    }

    @Test
    fun Github_에러_스낵바_재시도_클릭시_데이터를_로드를_다시_시도한다() {
        // 데이터 로드 실패 세팅
        repository.setFailureMode(true)

        composeTestRule.setContent {
            GithubScreen(viewModel = fakeViewModel)
        }

        runBlocking {
            fakeViewModel.fetchRepositories("next-step")
        }

        // 데이터 로드 성공 세팅
        repository.setFailureMode(false)
        repository.setFakeData(data)

        // 에러 발생 후 재시도 클릭
        composeTestRule.onNodeWithText("재시도")
            .performClick()

        // 데이터 로드 성공 검증
        data.forEach {
            composeTestRule.onNodeWithText(it.fullName)
                .assertExists()
        }
    }

    @Test
    fun 화면에_Star_갯수가_표시된다() {
        // 데이터 세팅
        repository.setFakeData(data)

        composeTestRule.setContent {
            GithubScreen(viewModel = fakeViewModel)
        }

        runBlocking {
            fakeViewModel.fetchRepositories("next-step")
        }

        data.forEach {
            composeTestRule.onNodeWithText(it.stars.toString())
                .assertExists()
        }
    }

    @Test
    fun Github_데이터중_star_갯수가_50_이상_이면_HOT이_표시된다() {
        // 데이터 세팅
        repository.setFakeData(data)

        composeTestRule.setContent {
            GithubScreen(viewModel = fakeViewModel)
        }

        runBlocking {
            fakeViewModel.fetchRepositories("next-step")
        }

        composeTestRule.onNodeWithText("next-step/nextstep-docs")
            .onParent()
            .assert(hasAnyDescendant(hasText("HOT")))
    }

    @Test
    fun Github_데이터중_star_갯수가_50_미만_이면_HOT이_표시되지_않는다() {
        // 데이터 세팅
        repository.setFakeData(data)

        composeTestRule.setContent {
            GithubScreen(viewModel = fakeViewModel)
        }

        runBlocking {
            fakeViewModel.fetchRepositories("next-step")
        }

        composeTestRule.onNodeWithText("next-step/haly-galy")
            .onParent()
            .assert(hasAnyDescendant(hasText("HOT").not()))
    }
}
