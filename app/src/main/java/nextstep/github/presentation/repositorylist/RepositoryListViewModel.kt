package nextstep.github.presentation.repositorylist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import nextstep.github.data.repository.GithubRepository
import nextstep.github.presentation.mapper.toUi

class RepositoryListViewModel(
    private val githubRepository: GithubRepository,
) : ViewModel() {

    private val _response = MutableStateFlow<List<Repository>>(emptyList())
    val response = _response.asStateFlow()

    fun getRepositories() {
        viewModelScope.launch {
            val repositories = githubRepository.getRepositories(REPO_NAME).map {
                it.toUi()
            }
            _response.update {
                repositories
            }
        }
    }

    companion object {
        private const val REPO_NAME = "next-step"
    }
}
