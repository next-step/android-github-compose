package nextstep.github.data

class FakeGithubRepository : GithubRepository {
    override suspend fun getRepositories(): Result<List<RepositoryEntity>> {
        return Result.success(
            listOf(
                RepositoryEntity("next-step", "코드스쿼드 NextStep의 저장소입니다.", 0),
                RepositoryEntity("next-step", "코드스쿼드 NextStep의 저장소입니다.", 50),
            )
        )
    }
}
