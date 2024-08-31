package nextstep.github.domain

class FakeGetGithubRepositoriesUseCase : GetGithubRepositoriesUseCase {
    override suspend fun getRepositories(): Result<List<Repository>> {
        return Result.success(
            listOf(
                Repository("next-step", "NextStep의 저장소입니다.", 0),
                Repository("next-step", "NextStep의 저장소입니다.", 50)
            )
        )
    }
}
