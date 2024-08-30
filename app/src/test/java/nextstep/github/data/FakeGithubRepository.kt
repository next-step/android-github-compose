package nextstep.github.data

class FakeGithubRepository : GithubRepository {
    override suspend fun getRepositories(organization: String): Result<List<RepositoryEntity>> {
        return Result.success(
            listOf(
                RepositoryEntity("next-step", "코드스쿼드 NextStep의 저장소입니다."),
                RepositoryEntity("next-step", "코드스쿼드 NextStep의 저장소입니다."),
                RepositoryEntity("next-step", "코드스쿼드 NextStep의 저장소입니다."),
                RepositoryEntity("next-step", "코드스쿼드 NextStep의 저장소입니다."),
                RepositoryEntity("next-step", "코드스쿼드 NextStep의 저장소입니다."),
                RepositoryEntity("next-step", "코드스쿼드 NextStep의 저장소입니다."),
                RepositoryEntity("next-step", "코드스쿼드 NextStep의 저장소입니다."),
                RepositoryEntity("next-step", "코드스쿼드 NextStep의 저장소입니다."),
                RepositoryEntity("next-step", "코드스쿼드 NextStep의 저장소입니다."),
                RepositoryEntity("next-step", "코드스쿼드 NextStep의 저장소입니다.")
            )
        )
    }
}