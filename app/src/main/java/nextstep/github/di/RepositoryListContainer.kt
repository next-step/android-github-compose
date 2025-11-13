package nextstep.github.di

import nextstep.github.data.repository.GithubRepository

class RepositoryListContainer(githubRepository: GithubRepository) {

    val repositoryListViewModelFactory = RepositoryListViewModelFactory(githubRepository)
}
