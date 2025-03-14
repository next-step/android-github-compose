package nextstep.github.data.repositories

import kotlinx.coroutines.flow.Flow
import nextstep.github.model.GitHubRepo

interface GithubRepoRepository {

    fun getGitHubReposStream(organization: String): Flow<List<GitHubRepo>>
}
