package nextstep.github.domain

class FakeRepositoryUseCase : RepositoryUseCase {
    override suspend fun getRepositories(): Result<List<Repository>> {
        return Result.success(
            listOf(
                Repository("next-step", "NextStep의 저장소입니다.", 0),
                Repository("next-step", "NextStep의 저장소입니다.", 50)
            )
        )
    }
}
