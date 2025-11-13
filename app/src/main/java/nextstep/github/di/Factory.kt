package nextstep.github.di

import nextstep.github.data.repository.GithubRepository
import nextstep.github.presentation.repositorylist.RepositoryListViewModel

interface Factory<T> {
    fun create(): T
}

class RepositoryListViewModelFactory(private val githubRepository: GithubRepository): Factory<RepositoryListViewModel> {

    override fun create(): RepositoryListViewModel {
        return RepositoryListViewModel(githubRepository)
    }
}
