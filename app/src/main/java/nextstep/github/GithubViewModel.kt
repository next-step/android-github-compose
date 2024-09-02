package nextstep.github

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import nextstep.github.core.data.GithubRepository
import nextstep.github.core.network.ApiResult

class GithubViewModel(
    private val githubRepository: GithubRepository
) : ViewModel() {

    fun getRepositories(organization: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                when (val result = githubRepository.getRepositories(organization)) {
                    is ApiResult.Success -> {
                        Log.d("GithubViewModel", "Success: ${result.value}")
                    }

                    is ApiResult.Error -> {
                        Log.e("GithubViewModel", "Error1: ${result.code} ${result.exception}")
                    }
                }
            } catch (e: Exception) {
                Log.e("GithubViewModel", "Error2: ${e.message}")
            }
        }
    }
}
