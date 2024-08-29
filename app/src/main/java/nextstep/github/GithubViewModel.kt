package nextstep.github

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nextstep.github.core.data.GithubRepository

class GithubViewModel(
    private val githubRepository: GithubRepository
) : ViewModel() {

    fun getRepositories(organization: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val result = githubRepository.getRepositories(organization)
            Log.d("TAG", "getRepositories: $result")
        }
    }
}
